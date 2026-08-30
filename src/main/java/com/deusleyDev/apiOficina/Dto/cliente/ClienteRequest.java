package com.deusleyDev.apiOficina.Dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 120, message = "Deve conter entra 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "Telefone é obrigatório")
        @Size (min = 10, max = 15, message = "Deve conter entre 10 a 15 caracteres")
        String telefone,

        @Email (message = "Email inválido, tente novamente")
        String email,

        @NotBlank(message = "cpf é obrigatório")
        @CPF(message = "CPF inválido")
        Double cpf

) {
}


