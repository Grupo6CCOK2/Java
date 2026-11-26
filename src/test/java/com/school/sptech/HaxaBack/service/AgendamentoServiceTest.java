
package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.entity.Agendamento;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.entity.Funcionario;
import com.school.sptech.HaxaBack.entity.Servico;
import com.school.sptech.HaxaBack.entity.StatusAgendamento;
import com.school.sptech.HaxaBack.exception.ObjetoDuplicadoException;
import com.school.sptech.HaxaBack.exception.ObjetoNaoEncontradoException;
import com.school.sptech.HaxaBack.repository.AgendamentoRepository;
import com.school.sptech.HaxaBack.repository.ClienteRepository;
import com.school.sptech.HaxaBack.repository.DependenteRepository;
import com.school.sptech.HaxaBack.repository.FuncionarioRepository;
import com.school.sptech.HaxaBack.repository.ItemServicoRepository;
import com.school.sptech.HaxaBack.repository.ServicoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private DependenteRepository dependenteRepository;

    @Mock
    private ServicoRepository servicoRepository;

    @Mock
    private ItemServicoRepository itemServicoRepository;

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Nested
    @DisplayName("Cenários método buscar")
    class BuscarTeste {

        @Test
        @DisplayName("Deve listar agendamentos com sucesso")
        void deveListarAgendamentosComSucesso() {
            Agendamento agendamento = new Agendamento();
            List<Agendamento> agendamentos = List.of(agendamento);

            Mockito.when(agendamentoRepository.findAll()).thenReturn(agendamentos);

            List<Agendamento> resultado = agendamentoService.buscar();

            Assertions.assertEquals(1, resultado.size());
        }
    }

    @Nested
    @DisplayName("Cenários método buscarPorId")
    class BuscarPorIdTeste {

        @Test
        @DisplayName("Deve buscar agendamento com sucesso")
        void deveBuscarAgendamentoComSucesso() {
            Integer id = 1;
            Agendamento agendamento = new Agendamento();
            Optional<Agendamento> agendamentoOptional = Optional.of(agendamento);

            Mockito.when(agendamentoRepository.findById(id)).thenReturn(agendamentoOptional);

            Agendamento resultado = agendamentoService.buscarPorId(id);

            Assertions.assertNotNull(resultado);
        }

        @Test
        @DisplayName("Deve validar agendamento não encontrado")
        void deveLancarExceptionQuandoAgendamentoNaoEncontrado() {
            Integer id = 1;

            Mockito.when(agendamentoRepository.findById(id)).thenReturn(Optional.empty());

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.buscarPorId(id));
        }
    }

    @Nested
    @DisplayName("Cenários método cadastrar")
    class CadastrarTeste {

        @Test
        @DisplayName("Deve cadastrar agendamento com sucesso")
        void deveCadastrarAgendamentoComSucesso() {
            Integer clienteId = 1;
            Integer funcionarioId = 2;
            Integer dependenteId = null;
            Integer servicoId = 1;

            Cliente cliente = new Cliente();
            Funcionario funcionario = new Funcionario();
            Servico servico = new Servico();
            servico.setValor(BigDecimal.valueOf(50.0));

            Agendamento agendamento = new Agendamento();

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(cliente));
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.of(funcionario));
            Mockito.when(servicoRepository.findById(servicoId))
                    .thenReturn(Optional.of(servico));
            Mockito.when(agendamentoRepository.existsByDataAndHorarioAndFuncionarioIdAndStatusNot(
                    agendamento.getData(), agendamento.getHorario(), funcionarioId,
                    StatusAgendamento.Cancelado)).thenReturn(false);
            Mockito.when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);
            Mockito.when(itemServicoRepository.saveAll(Mockito.anyList()))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            Agendamento resultado = agendamentoService.cadastrar(
                    agendamento, clienteId, funcionarioId, dependenteId, List.of(servicoId));

            Assertions.assertNotNull(resultado);
            Assertions.assertEquals(cliente, resultado.getFkUsuario());
            Assertions.assertEquals(funcionario, resultado.getFuncionario());
            Assertions.assertEquals(1, resultado.getItensServico().size());

            Mockito.verify(agendamentoRepository, Mockito.times(1)).save(agendamento);
        }

        @Test
        @DisplayName("Deve validar cliente não encontrado")
        void deveLancarExceptionQuandoClienteNaoEncontrado() {
            Integer clienteId = 1;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.cadastrar(
                            new Agendamento(), clienteId, 2, null, List.of(1)));
        }

        @Test
        @DisplayName("Deve validar funcionário não encontrado")
        void deveLancarExceptionQuandoFuncionarioNaoEncontrado() {
            Integer clienteId = 1;
            Integer funcionarioId = 2;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.cadastrar(
                            new Agendamento(), clienteId, funcionarioId, null, List.of(1)));
        }

        @Test
        @DisplayName("Deve validar dependente não encontrado")
        void deveLancarExceptionQuandoDependenteNaoEncontrado() {
            Integer clienteId = 1;
            Integer funcionarioId = 2;
            Integer dependenteId = 3;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.of(new Funcionario()));
            Mockito.when(dependenteRepository.findByIdDependentesAndUsuarioId(dependenteId, clienteId))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.cadastrar(
                            new Agendamento(), clienteId, funcionarioId, dependenteId, List.of(1)));
        }

        @Test
        @DisplayName("Deve validar serviço não encontrado")
        void deveLancarExceptionQuandoServicoNaoEncontrado() {
            Integer clienteId = 1;
            Integer funcionarioId = 2;
            Integer servicoId = 3;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.of(new Funcionario()));
            Mockito.when(servicoRepository.findById(servicoId))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.cadastrar(
                            new Agendamento(), clienteId, funcionarioId, null, List.of(servicoId)));
        }

        @Test
        @DisplayName("Deve validar agendamento duplicado")
        void deveLancarExceptionQuandoAgendamentoDuplicado() {
            Integer clienteId = 1;
            Integer funcionarioId = 2;
            Integer servicoId = 3;

            Agendamento agendamento = new Agendamento();

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.of(new Funcionario()));
            Mockito.when(servicoRepository.findById(servicoId))
                    .thenReturn(Optional.of(new Servico()));
            Mockito.when(agendamentoRepository.existsByDataAndHorarioAndFuncionarioIdAndStatusNot(
                    agendamento.getData(), agendamento.getHorario(), funcionarioId,
                    StatusAgendamento.Cancelado)).thenReturn(true);

            Assertions.assertThrows(ObjetoDuplicadoException.class,
                    () -> agendamentoService.cadastrar(
                            agendamento, clienteId, funcionarioId, null, List.of(servicoId)));

            Mockito.verify(agendamentoRepository, Mockito.never()).save(agendamento);
        }
    }

    @Nested
    @DisplayName("Cenários método atualizar")
    class AtualizarTeste {

        @Test
        @DisplayName("Deve atualizar agendamento com sucesso")
        void deveAtualizarAgendamentoComSucesso() {
            Integer id = 1;
            Integer clienteId = 2;
            Integer funcionarioId = 3;
            Integer servicoId = 4;

            Cliente cliente = new Cliente();
            Funcionario funcionario = new Funcionario();
            Servico servico = new Servico();
            servico.setValor(BigDecimal.valueOf(50.0));

            Agendamento agendamento = new Agendamento();

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(cliente));
            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(true);
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.of(funcionario));
            Mockito.when(servicoRepository.findById(servicoId))
                    .thenReturn(Optional.of(servico));
            Mockito.when(agendamentoRepository.existsByDataAndHorarioAndFuncionarioIdAndStatusNotAndIdNot(
                    agendamento.getData(), agendamento.getHorario(), funcionarioId,
                    StatusAgendamento.Cancelado, id)).thenReturn(false);
            Mockito.when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);
            Mockito.when(itemServicoRepository.saveAll(Mockito.anyList()))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            Agendamento resultado = agendamentoService.atualizar(
                    id, agendamento, clienteId, funcionarioId, null, List.of(servicoId));

            Assertions.assertEquals(id, resultado.getId());
            Assertions.assertEquals(cliente, resultado.getFkUsuario());
            Assertions.assertEquals(funcionario, resultado.getFuncionario());

            Mockito.verify(itemServicoRepository).deleteByAgendamentoId(id);
            Mockito.verify(itemServicoRepository).flush();
        }

        @Test
        @DisplayName("Deve validar agendamento não encontrado")
        void deveLancarExceptionQuandoAgendamentoNaoEncontrado() {
            Integer id = 1;
            Integer clienteId = 2;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(false);

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.atualizar(
                            id, new Agendamento(), clienteId, 3, null, List.of(4)));
        }

        @Test
        @DisplayName("Deve validar cliente não encontrado")
        void deveLancarExceptionQuandoClienteNaoEncontrado() {
            Integer id = 1;
            Integer clienteId = 2;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.empty());
            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(true);

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.atualizar(
                            id, new Agendamento(), clienteId, 3, null, List.of(4)));
        }

        @Test
        @DisplayName("Deve validar funcionário não encontrado")
        void deveLancarExceptionQuandoFuncionarioNaoEncontrado() {
            Integer id = 1;
            Integer clienteId = 2;
            Integer funcionarioId = 3;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(true);
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.atualizar(
                            id, new Agendamento(), clienteId, funcionarioId, null, List.of(4)));
        }

        @Test
        @DisplayName("Deve validar dependente não encontrado")
        void deveLancarExceptionQuandoDependenteNaoEncontrado() {
            Integer id = 1;
            Integer clienteId = 2;
            Integer funcionarioId = 3;
            Integer dependenteId = 4;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(true);
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.of(new Funcionario()));
            Mockito.when(dependenteRepository.findByIdDependentesAndUsuarioId(dependenteId, clienteId))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.atualizar(
                            id, new Agendamento(), clienteId, funcionarioId, dependenteId, List.of(4)));
        }

        @Test
        @DisplayName("Deve validar serviço não encontrado")
        void deveLancarExceptionQuandoServicoNaoEncontrado() {
            Integer id = 1;
            Integer clienteId = 2;
            Integer funcionarioId = 3;
            Integer servicoId = 4;

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(true);
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.of(new Funcionario()));
            Mockito.when(servicoRepository.findById(servicoId))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.atualizar(
                            id, new Agendamento(), clienteId, funcionarioId, null, List.of(servicoId)));
        }

        @Test
        @DisplayName("Deve validar agendamento duplicado")
        void deveLancarExceptionQuandoAgendamentoDuplicado() {
            Integer id = 1;
            Integer clienteId = 2;
            Integer funcionarioId = 3;
            Integer servicoId = 4;

            Agendamento agendamento = new Agendamento();

            Mockito.when(clienteRepository.findById(clienteId))
                    .thenReturn(Optional.of(new Cliente()));
            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(true);
            Mockito.when(funcionarioRepository.findById(funcionarioId))
                    .thenReturn(Optional.of(new Funcionario()));
            Mockito.when(servicoRepository.findById(servicoId))
                    .thenReturn(Optional.of(new Servico()));
            Mockito.when(agendamentoRepository.existsByDataAndHorarioAndFuncionarioIdAndStatusNotAndIdNot(
                    agendamento.getData(), agendamento.getHorario(), funcionarioId,
                    StatusAgendamento.Cancelado, id)).thenReturn(true);

            Assertions.assertThrows(ObjetoDuplicadoException.class,
                    () -> agendamentoService.atualizar(
                            id, agendamento, clienteId, funcionarioId, null, List.of(servicoId)));

            Mockito.verify(agendamentoRepository, Mockito.never()).save(agendamento);
        }
    }

    @Nested
    @DisplayName("Cenários método deletar")
    class DeletarTeste {

        @Test
        @DisplayName("Deve deletar agendamento com sucesso")
        void deveDeletarAgendamentoComSucesso() {
            Integer id = 1;

            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(true);

            Assertions.assertDoesNotThrow(() -> agendamentoService.deletar(id));

            Mockito.verify(itemServicoRepository).deleteByAgendamentoId(id);
            Mockito.verify(itemServicoRepository).flush();
            Mockito.verify(agendamentoRepository).deleteById(id);
        }

        @Test
        @DisplayName("Deve validar agendamento não encontrado")
        void deveLancarExceptionQuandoAgendamentoNaoEncontrado() {
            Integer id = 1;

            Mockito.when(agendamentoRepository.existsById(id)).thenReturn(false);

            Assertions.assertThrows(ObjetoNaoEncontradoException.class,
                    () -> agendamentoService.deletar(id));

            Mockito.verify(agendamentoRepository, Mockito.never()).deleteById(id);
        }
    }
}
