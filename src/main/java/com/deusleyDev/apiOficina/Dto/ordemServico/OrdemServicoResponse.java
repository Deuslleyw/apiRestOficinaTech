package com.deusleyDev.apiOficina.Dto.ordemServico;

import com.deusleyDev.apiOficina.Dto.cliente.ClienteResponse;
import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoResponse;

public record OrdemServicoResponse(

        Long id,
        String descricao,
        Double valor,
        String status,
        ClienteResponse cliente,
        VeiculoResponse veiculo

) {
}
