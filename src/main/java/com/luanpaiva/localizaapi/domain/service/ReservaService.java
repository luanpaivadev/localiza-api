package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.ReservaInput;
import com.luanpaiva.localizaapi.domain.exception.ReservaNaoEncontradaException;
import com.luanpaiva.localizaapi.domain.exception.VeiculoNaoDisponivelException;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.model.DadosEmail;
import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.port.ClienteServicePort;
import com.luanpaiva.localizaapi.domain.port.EnvioEmailPort;
import com.luanpaiva.localizaapi.domain.port.ReservaRepositoryPort;
import com.luanpaiva.localizaapi.domain.port.ReservaServicePort;
import com.luanpaiva.localizaapi.domain.port.VeiculoServicePort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.luanpaiva.localizaapi.domain.model.StatusReserva.ABERTO;
import static com.luanpaiva.localizaapi.domain.model.StatusReserva.FECHADO;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.math.BigDecimal.valueOf;
import static java.text.MessageFormat.format;
import static java.time.Duration.between;

public class ReservaService implements ReservaServicePort {

    private static final Boolean NAO = FALSE;
    private static final Boolean SIM = TRUE;
    private static final String O_VEICULO_NAO_ESTA_DISPONIVEL_PARA_RESERVA_NO_MOMENTO = "O veículo com placa {0}, não está disponível para reserva no momento";
    private static final String CONTRATO_DE_RESERVA_COM_ID_0_NAO_LOCALIZADO = "Contrato de reserva com id {0} não localizado.";

    private final ReservaRepositoryPort reservaRepositoryPort;
    private final ClienteServicePort clienteServicePort;
    private final VeiculoServicePort veiculoServicePort;
    private final EnvioEmailPort envioEmailPort;

    public ReservaService(ReservaRepositoryPort reservaRepositoryPort, ClienteServicePort clienteServicePort, VeiculoServicePort veiculoServicePort, EnvioEmailPort envioEmailPort) {
        this.reservaRepositoryPort = reservaRepositoryPort;
        this.clienteServicePort = clienteServicePort;
        this.veiculoServicePort = veiculoServicePort;
        this.envioEmailPort = envioEmailPort;
    }

    @Override
    public List<Reserva> listarReservas(StatusReserva statusReserva) {
        return reservaRepositoryPort.findByStatus(statusReserva);
    }

    @Override
    public Reserva salvarReserva(ReservaInput reservaInput) {

        String cpf = reservaInput.getCpfCliente();
        Cliente cliente = clienteServicePort.buscarClientePeloCpf(cpf);

        String placaVeiculo = reservaInput.getPlacaVeiculo();
        Veiculo veiculo = veiculoServicePort.buscarVeiculoPelaPlaca(placaVeiculo);

        if (veiculo.getDisponivel().equals(NAO)) {
            throw new VeiculoNaoDisponivelException(format(O_VEICULO_NAO_ESTA_DISPONIVEL_PARA_RESERVA_NO_MOMENTO, placaVeiculo));
        }

        veiculo.setDisponivel(NAO);
        LocalDateTime dataHoraRetirada = reservaInput.getDataHoraRetirada();
        LocalDateTime dataHoraDevolucaoPrevista = reservaInput.getDataHoraDevolucao();

        BigDecimal valorPrevisto = calcularCustoReserva(dataHoraRetirada, dataHoraDevolucaoPrevista, veiculo.getValorDiariaReserva());

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);
        reserva.setDataHoraRetirada(dataHoraRetirada);
        reserva.setDataHoraDevolucaoPrevista(dataHoraDevolucaoPrevista);
        reserva.setValorPrevisto(valorPrevisto);
        reserva.setValorExcedente(BigDecimal.ZERO);
        reserva.setValorTotal(reserva.getValorPrevisto().add(reserva.getValorExcedente()));
        reserva.setStatusReserva(ABERTO);

        reserva = reservaRepositoryPort.save(reserva);
        veiculoServicePort.salvarVeiculo(veiculo);
        enviarEmail(cliente, reserva);

        return reserva;
    }

    @Override
    public Reserva finalizarReserva(Long id, LocalDateTime dataHoraDevolucaoEfetivada) {

        Reserva reserva = reservaRepositoryPort.findById(id)
                .orElseThrow(() -> new ReservaNaoEncontradaException(format(CONTRATO_DE_RESERVA_COM_ID_0_NAO_LOCALIZADO, id)));

        reserva.setDataHoraDevolucaoEfetivada(dataHoraDevolucaoEfetivada);
        BigDecimal valorExcedente = calcularValorExcedente(reserva, dataHoraDevolucaoEfetivada);
        reserva.setValorExcedente(valorExcedente);
        reserva.setValorTotal(reserva.getValorPrevisto().add(reserva.getValorExcedente()));
        reserva.getVeiculo().setDisponivel(SIM);
        reserva.setStatusReserva(FECHADO);

        return reservaRepositoryPort.save(reserva);
    }

    private BigDecimal calcularCustoReserva(LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucao,
                                            BigDecimal valorDiaria) {

        Duration duracaoReserva = between(dataHoraRetirada, dataHoraDevolucao);
        BigDecimal duracaoHoras = valueOf((double) duracaoReserva.toMinutes() / 60);
        BigDecimal custoPorHora = valueOf(valorDiaria.doubleValue() / 24);

        return duracaoHoras.multiply(custoPorHora).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularValorExcedente(Reserva reserva, LocalDateTime dataHoraDevolucaoEfetivada) {

        LocalDateTime dataHoraDevolucaoPrevista = reserva.getDataHoraDevolucaoPrevista();
        BigDecimal valorDiaria = reserva.getVeiculo().getValorDiariaReserva();
        BigDecimal custoPorHora = valueOf(valorDiaria.doubleValue() / 24);

        Duration duration = between(dataHoraDevolucaoPrevista, dataHoraDevolucaoEfetivada);
        BigDecimal horaExcedente = valueOf((double) duration.toMinutes() / 60);
        if (horaExcedente.compareTo(BigDecimal.valueOf(1)) <= 0) {
            return BigDecimal.ZERO;
        }

        return horaExcedente.multiply(custoPorHora).setScale(2, RoundingMode.HALF_UP);
    }

    private void enviarEmail(Cliente cliente, Reserva reserva) {

        DadosEmail dadosEmail = new DadosEmail(
                cliente.getEmail(),
                format("{0}, sua reserva {1} está confirmada.", cliente.getNome(), reserva.getId())
        );

        envioEmailPort.enviarEmail(dadosEmail, reserva);
    }
}
