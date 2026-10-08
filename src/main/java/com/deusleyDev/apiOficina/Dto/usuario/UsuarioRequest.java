package com.deusleyDev.apiOficina.Dto.usuario;

import com.deusleyDev.apiOficina.enuns.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String senha,

        @NotNull(message = "Perfil é obrigatório")
        PerfilUsuario perfil
) {
}
