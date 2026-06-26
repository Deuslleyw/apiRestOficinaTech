package com.deusleyDev.apiOficina.Dto.ordemServico;

import com.deusleyDev.apiOficina.Dto.cliente.ClienteResponse;
import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoResponse;
import com.deusleyDev.apiOficina.enuns.StatusOrdem;

import java.time.LocalDateTime;

public record OrdemServicoResponse(

        Long id,
        String descricao,
        Double valor,
        StatusOrdem status,
        LocalDateTime dataAbertura,
        LocalDateTime dataFechamento,
        ClienteResponse cliente,
        VeiculoResponse veiculo

) {
}
