CREATE DATABASE IF NOT EXISTS HexaDevs2;
USE HexaDevs2;

CREATE TABLE Usuario (
                         idUsuario INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(45) NOT NULL,
                         sobrenome VARCHAR(45) NOT NULL,
                         email VARCHAR(45) NOT NULL UNIQUE,
                         telefone VARCHAR(11),
                         senha VARCHAR(60) NOT NULL,
                         ativo TINYINT(1) DEFAULT 1,
                         dataCadastro DATETIME,
                         dt_nascimento DATE,
                         tipo_usuario TINYINT(1)
);

CREATE TABLE Sessao (
                        idSessao INT AUTO_INCREMENT PRIMARY KEY,
                        Usuario_idUsuario INT NOT NULL,
                        jti VARCHAR(36) NOT NULL,
                        Ativa BOOLEAN NOT NULL DEFAULT TRUE,
                        CONSTRAINT uk_sessao_jti UNIQUE (jti),
                        CONSTRAINT fk_sessao_usuario FOREIGN KEY (Usuario_idUsuario)
                            REFERENCES Usuario(idUsuario)
);

CREATE TABLE Funcionario (
                             idFuncionario INT AUTO_INCREMENT PRIMARY KEY,
                             Usuario_idUsuario INT NOT NULL UNIQUE,
                             FOREIGN KEY (Usuario_idUsuario) REFERENCES Usuario(idUsuario)
);

CREATE TABLE Dependentes (
                             idDependentes INT AUTO_INCREMENT,
                             nome VARCHAR(45) NOT NULL,
                             parentesco VARCHAR(45) NOT NULL,
                             Usuario_idUsuario INT NOT NULL,
                             PRIMARY KEY (idDependentes, Usuario_idUsuario),
                             FOREIGN KEY (Usuario_idUsuario) REFERENCES Usuario(idUsuario)
);

CREATE TABLE Servico (
                         idServico INT AUTO_INCREMENT PRIMARY KEY,
                         tipoServico VARCHAR(45) NOT NULL,
                         valor DECIMAL(10,2) NOT NULL
);

-- Serviços que cada funcionário está habilitado a realizar
CREATE TABLE Servico_func (
                              Funcionario_idFuncionario INT NOT NULL,
                              Servico_idServico INT NOT NULL,
                              tempo_duracao TIME,
                              PRIMARY KEY (Funcionario_idFuncionario, Servico_idServico),
                              FOREIGN KEY (Funcionario_idFuncionario)
                                  REFERENCES Funcionario(idFuncionario),
                              FOREIGN KEY (Servico_idServico)
                                  REFERENCES Servico(idServico)
);

CREATE TABLE Agendamento (
                             idAgendamento INT AUTO_INCREMENT PRIMARY KEY,
                             data DATE NOT NULL,
                             horario TIME NOT NULL,
                             status ENUM('Pendente', 'Confirmado', 'Cancelado', 'Concluido')
        DEFAULT 'Pendente',
                             Dependentes_idDependentes INT,
                             Dependentes_Usuario_idUsuario INT,
                             Usuario_idUsuario INT NOT NULL,
                             Funcionario_idFuncionario INT NOT NULL,
                             FOREIGN KEY (Dependentes_idDependentes, Dependentes_Usuario_idUsuario)
                                 REFERENCES Dependentes(idDependentes, Usuario_idUsuario),
                             FOREIGN KEY (Usuario_idUsuario) REFERENCES Usuario(idUsuario),
                             FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario(idFuncionario)
);

CREATE TABLE itemServico (
                             Agendamento_idAgendamento INT NOT NULL,
                             Servico_idServico INT NOT NULL,
                             preco_unitario DECIMAL(10,2) NOT NULL,
                             PRIMARY KEY (Agendamento_idAgendamento, Servico_idServico),
                             FOREIGN KEY (Agendamento_idAgendamento) REFERENCES Agendamento(idAgendamento),
                             FOREIGN KEY (Servico_idServico) REFERENCES Servico(idServico)
);


-- Senhas originais (hash BCrypt): Ana=senha123, Carlos=senha456, Mariana=senha789, Fred=admin123, Juliana=senha999, Marcos=senha333
INSERT INTO Usuario
(idUsuario, nome, sobrenome, email, telefone, senha, ativo,
 dataCadastro, dt_nascimento, tipo_usuario)
VALUES
    (1, 'Ana', 'Silva', 'ana.silva@email.com', '11988887777',
     '$2a$10$/30HJw6DU8UXCUhlVUy82eln74VRC7ZfbBOEfWYAOBOfIO2zTXWv.', 1, '2026-01-10 10:00:00', '1990-05-15', 0),
    (2, 'Carlos', 'Oliveira', 'carlos.oli@email.com', '11977776666',
     '$2a$10$76zz6iX4YB0gszKQne43K.3psfWsRu8IspyBOnamwAOBYtUr.I2ia', 1, '2026-01-12 11:30:00', '1985-08-20', 1),
    (3, 'Mariana', 'Souza', 'mariana.souza@email.com', '11966665555',
     '$2a$10$d9vudfyKiFju5F/WANzurObN/fTuJn/nF87mu0XivIXgmO.6isGvW', 1, '2026-01-15 14:20:00', '1992-12-01', 0),
    (4, 'Fred', 'Admin', 'fred.admin@email.com', '11955554444',
     '$2a$10$jB3/ccpHyt1IsYlQCxpcl.gPyDrKYTV3KKrlyycyU/udT8jVR.b5y', 1, '2026-01-01 08:00:00', '1980-03-10', 2),
    (5, 'Juliana', 'Lima', 'juliana.lima@email.com', '11944443333',
     '$2a$10$RjTmqO3DBPdw5LF9q1bLRepLHJq1tIoc/COLV.mIbptnOrTz3k1mK', 1, '2026-02-01 09:00:00', '1988-04-12', 1),
    (6, 'Marcos', 'Santos', 'marcos.santos@email.com', '11933332222',
     '$2a$10$kJkcKD1Tf2DFRI9csxY5f.wm.HGEZ5SovSh6xGDCpqtgijrHXS9Mi', 1, '2026-02-10 14:00:00', '1995-11-25', 0);

INSERT INTO Funcionario (idFuncionario, Usuario_idUsuario)
VALUES (1, 2), (2, 5);

INSERT INTO Dependentes (idDependentes, nome, parentesco, Usuario_idUsuario)
VALUES
    (1, 'Lucas Silva', 'Filho', 1),
    (2, 'Beatriz Souza', 'Filha', 3);

INSERT INTO Servico (idServico, tipoServico, valor)
VALUES
    (1, 'Corte de Cabelo', 50.00),
    (2, 'Manicure', 30.00),
    (3, 'Massagem Relaxante', 120.00);

INSERT INTO Servico_func
(Funcionario_idFuncionario, Servico_idServico, tempo_duracao)
VALUES
    (1, 1, '00:45:00'),
    (1, 3, '01:00:00'),
    (2, 2, '00:30:00'),
    (2,1, '00:30:00');

INSERT INTO Agendamento (idAgendamento, data, horario, status,
                         Dependentes_idDependentes, Dependentes_Usuario_idUsuario,
                         Usuario_idUsuario, Funcionario_idFuncionario)
VALUES
    (1, '2026-03-20', '14:00:00', 'Confirmado', NULL, NULL, 1, 1),
    (2, '2026-03-20', '10:30:00', 'Pendente', 1, 1, 1, 2),
    (3, '2026-03-20', '15:30:00', 'Confirmado', NULL, NULL, 6, 1),
    (4, '2026-03-20', '17:00:00', 'Confirmado', 2, 3, 3, 1);


INSERT INTO itemServico (Agendamento_idAgendamento, Servico_idServico, preco_unitario)
VALUES
    (1, 1, 50.00),
    (2, 2, 30.00),
    (3, 1, 50.00),
    (4, 3, 120.00);

-- 1. Todos os agendamentos, cliente, serviço e funcionário escolhido
SELECT
    a.idAgendamento,
    a.data,
    a.horario,
    a.status,
    COALESCE(d.nome, cliente.nome) AS cliente_atendido,
    s.tipoServico,
    funcionario_usuario.nome AS funcionario_nome,
    funcionario_usuario.sobrenome AS funcionario_sobrenome
FROM Agendamento AS a
         JOIN Usuario AS cliente
              ON a.Usuario_idUsuario = cliente.idUsuario
         LEFT JOIN Dependentes AS d
                   ON a.Dependentes_idDependentes = d.idDependentes
                       AND a.Dependentes_Usuario_idUsuario = d.Usuario_idUsuario
         JOIN Funcionario AS f
              ON a.Funcionario_idFuncionario = f.idFuncionario
         JOIN Usuario AS funcionario_usuario
              ON f.Usuario_idUsuario = funcionario_usuario.idUsuario
         JOIN itemServico AS item
              ON a.idAgendamento = item.Agendamento_idAgendamento
         JOIN Servico AS s
              ON item.Servico_idServico = s.idServico
ORDER BY a.data, a.horario;

-- 2. Lista de dependentes
SELECT
    idDependentes,
    nome,
    parentesco,
    Usuario_idUsuario
FROM Dependentes;

-- 3. Agendamentos feitos para dependentes
SELECT
    a.idAgendamento,
    a.data,
    a.horario,
    a.status,
    d.nome AS dependente_nome,
    d.parentesco,
    titular.nome AS titular_nome
FROM Agendamento AS a
         JOIN Dependentes AS d
              ON a.Dependentes_idDependentes = d.idDependentes
                  AND a.Dependentes_Usuario_idUsuario = d.Usuario_idUsuario
         JOIN Usuario AS titular
              ON d.Usuario_idUsuario = titular.idUsuario
WHERE a.Dependentes_idDependentes IS NOT NULL;

-- 4. Todos os atendimentos de um dia específico
SELECT
    a.idAgendamento,
    a.data,
    a.horario,
    a.status,
    COALESCE(d.nome, titular.nome) AS pessoa_atendida,
    CASE
        WHEN a.Dependentes_idDependentes IS NOT NULL THEN 'Dependente'
        ELSE 'Titular'
        END AS tipo_cliente,
    s.tipoServico,
    item.preco_unitario
FROM Agendamento AS a
         JOIN Usuario AS titular
              ON a.Usuario_idUsuario = titular.idUsuario
         LEFT JOIN Dependentes AS d
                   ON a.Dependentes_idDependentes = d.idDependentes
                       AND a.Dependentes_Usuario_idUsuario = d.Usuario_idUsuario
         JOIN itemServico AS item
              ON a.idAgendamento = item.Agendamento_idAgendamento
         JOIN Servico AS s
              ON item.Servico_idServico = s.idServico
WHERE a.data = '2026-03-20'
ORDER BY a.horario;

-- 5. esse select mostra todos os agendamentos que o funcionario tem naquele dia, tanto de titulares, tanto de dependentes
SELECT
    a.idAgendamento,
    a.data,
    a.horario,
    a.status,
    COALESCE(d.nome, titular.nome) AS cliente_atendido,
    CASE
        WHEN a.Dependentes_idDependentes IS NOT NULL THEN 'Dependente'
        ELSE 'Titular'
        END AS tipo_cliente,
    s.tipoServico
FROM Agendamento AS a
         JOIN Usuario AS titular
              ON a.Usuario_idUsuario = titular.idUsuario
         LEFT JOIN Dependentes AS d
                   ON a.Dependentes_idDependentes = d.idDependentes
                       AND a.Dependentes_Usuario_idUsuario = d.Usuario_idUsuario
         JOIN itemServico AS item
              ON a.idAgendamento = item.Agendamento_idAgendamento
         JOIN Servico AS s
              ON item.Servico_idServico = s.idServico
WHERE a.Funcionario_idFuncionario = 2
  AND a.data = '2026-03-20'
ORDER BY a.horario;

-- 6. Serviços que um funcionário pode realizar
SELECT
    f.idFuncionario,
    u.nome,
    u.sobrenome,
    s.idServico,
    s.tipoServico,
    sf.tempo_duracao
FROM Funcionario AS f
         JOIN Usuario AS u
              ON f.Usuario_idUsuario = u.idUsuario
         JOIN Servico_func AS sf
              ON f.idFuncionario = sf.Funcionario_idFuncionario
         JOIN Servico AS s
              ON sf.Servico_idServico = s.idServico
WHERE f.idFuncionario = 2;
