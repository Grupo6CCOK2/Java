# Fluxo de Autenticação JWT (HexaBack)

Resumo rápido de como funciona o login e a segurança no projeto para o grupo não se perder ao criar ou alterar código.

---

## 1. Visão Geral das Classes

### Pacote `config`
- **`SecurityConfiguracao`**: Define quais rotas são públicas (`URLS_PERMITIDAS`, como `/clientes/login`, `/clientes/logout` e Swagger) e quais exigem login, além de configurar o CORS e o `BCryptPasswordEncoder`.
- **`AutenticacaoFilter`**: Intercepta **toda requisição** que chega na API. Procura o token JWT no cookie `authToken` ou no header `Authorization: Bearer <token>`, valida o token e libera o usuário.
- **`AutenticacaoProvider`**: Chamado na hora do login para comparar a senha digitada com o hash BCrypt salvo no banco.
- **`AutenticacaoEntryPoint`**: Retorna `401 Unauthorized` quando alguém tenta acessar uma rota protegida sem token ou com credenciais inválidas, e `403 Forbidden` quando não tem permissão.
- **`GerenciadorTokenJwt`**: Cria, assina e valida o token JWT usando a chave `jwt.secret` e o tempo `jwt.validity` do `application.properties`.

### Pacote `service` e `dto`
- **`AutenticacaoService`**: Busca o usuário no banco pelo e-mail (`clienteRepository.findByEmail`) e devolve um `ClienteDetalhesDto`.
- **`ClienteDetalhesDto`**: Adapta a entidade `Cliente` para o formato que o Spring Security entende (`UserDetails`), definindo a role (`ROLE_FUNCIONARIO` se `tipoUsuario = true`, ou `ROLE_CLIENTE` se `false`).
- **`ClienteLoginDto`**: Recebe `{ email, senha }` no login.
- **`ClienteTokenDto`**: Resposta do login contendo `{ userId, nome, email, token }`.

---

## 2. Fluxo de Dados Passo a Passo

### A) Cadastro de Cliente/Funcionário (`POST /clientes`)
1. O `ClienteController` recebe o `ClienteRequestDto`.
2. No `ClienteService.cadastarCliente()`, a senha é criptografada com `passwordEncoder.encode(senha)` (BCrypt) **antes** de salvar no MySQL.
3. O banco salva apenas o hash de 60 caracteres (nunca a senha pura).

### B) Login (`POST /clientes/login`)
1. O usuário envia `{ "email": "...", "senha": "..." }`.
2. O `ClienteController` chama `clienteService.autenticar()`.
3. O `AuthenticationManager` aciona o `AutenticacaoProvider`, que:
   - Busca o cliente no banco via `AutenticacaoService.loadUserByUsername(email)`.
   - Compara a senha enviada com o hash do banco usando `passwordEncoder.matches()`.
4. Se estiver tudo certo, o `GerenciadorTokenJwt` gera o token JWT.
5. O `ClienteController` devolve o `ClienteTokenDto` no corpo da resposta e também grava o token no cookie HttpOnly `authToken`.

### C) Acessando Endpoints Protegidos (ex: `GET /clientes`, `/servicos`)
1. O cliente envia a requisição (com o cookie `authToken` ou com o header `Authorization: Bearer <token>`).
2. O `AutenticacaoFilter` lê o token, valida a assinatura e a validade no `GerenciadorTokenJwt` e autentica a requisição no Spring Security.
3. A requisição chega normalmente na Controller solicitada.

### D) Logout (`POST /clientes/logout`)
1. O `ClienteController` sobrescreve o cookie `authToken` com tempo de vida zero (`maxAge(0)`), removendo-o do navegador.

---

## 3. Regras Práticas para o Grupo

1. **Testando no Swagger (`http://localhost:8080/swagger-ui.html`)**:
   - Faça login em `POST /clientes/login` (o `data.sql` já cria o usuário de teste `john@doe.com` com senha `123456`).
   - Copie o `token` retornado ou deixe o cookie automático agir; se quiser usar o botão **Authorize** do Swagger, cole o token lá.
2. **Criando uma nova Controller**:
   - Coloque `@SecurityRequirement(name = "Bearer")` em cima dos métodos que precisam de autenticação para que o Swagger envie o token.
   - Se algum endpoint novo precisar ser público (acessado sem login), adicione o caminho dele no array `URLS_PERMITIDAS` dentro de `SecurityConfiguracao.java`.
3. **Banco de Dados (MySQL)**:
   - A coluna `senha` na tabela `Usuario` precisa ter pelo menos `VARCHAR(60)` (ou `VARCHAR(255)`), pois o hash do BCrypt tem 60 caracteres.
   - Não faça `INSERT` manual no banco com senha em texto puro (ex: `'123456'`), pois o BCrypt só aceita senhas criptografadas.
