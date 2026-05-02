package com.deusleyDev.apiOficina.Dto.ordemServico;

public record OrdemServicoRequest(

        String descricao,
        Double valor,
        String status,
        Long clienteId,
        Long veiculoId

) {
}
