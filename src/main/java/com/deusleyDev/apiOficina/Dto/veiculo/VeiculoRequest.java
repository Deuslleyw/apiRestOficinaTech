package com.deusleyDev.apiOficina.Dto.veiculo;

import jakarta.validation.constraints.*;

public record VeiculoRequest(

        @NotBlank(message = "Placa é obrigatório")
        @Size( min = 7 , max = 8, message = "Placa inválida")
        String placa,

        @NotBlank(message = "Modelo é obrigatório")
        @Size(min = 3 , max = 50)
        String modelo,

        @NotBlank(message = "Marca é obrigatório")
        @Size(min = 3 , max = 50)
        String marca,

        @NotNull(message = "Ano é obrigatório")
        @Min(value = 1900, message = "Ano inválido")
        @Max(value = 2100, message = "Ano inválido")
        String ano,

        @NotNull(message = "Cliente é obrigatório")
        Long clienteId
) {

}




