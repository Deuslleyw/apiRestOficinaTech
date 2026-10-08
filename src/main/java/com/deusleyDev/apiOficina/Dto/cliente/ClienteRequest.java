package com.deusleyDev.apiOficina.Dto.cliente;

import com.deusleyDev.apiOficina.util.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 120, message = "Deve conter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "Telefone é obrigatório")
        @Phone
        String telefone,

        @Email (message = "Email inválido, tente novamente")
        String email,

        @NotBlank(message = "Cpf é obrigatório")
        @CPF(message = "CPF inválido, verifique e tente novamente!")
        String cpf

) {
}


