package com.deusleyDev.apiOficina.Dto.veiculo;

public record VeiculoResponse(

        Long id,
        String placa,
        String modelo,
        String marca,
        String ano,
        Long clienteId,
        String clienteNome

        ) {

}


