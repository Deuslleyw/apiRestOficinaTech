package com.deusleyDev.apiOficina.Dto.cliente;

public record ClienteRequest(

        String nome,
        String telefone,
        String email,
        Double cpf

) {
}


