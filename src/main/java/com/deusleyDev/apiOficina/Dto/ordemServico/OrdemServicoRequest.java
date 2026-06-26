package com.deusleyDev.apiOficina.Dto.ordemServico;

import com.deusleyDev.apiOficina.enuns.StatusOrdem;

public record OrdemServicoRequest(

        String descricao,
        Double valor,
        StatusOrdem status,
        Long clienteId,
        Long veiculoId

) {
}
