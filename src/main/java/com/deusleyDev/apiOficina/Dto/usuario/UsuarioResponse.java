package com.deusleyDev.apiOficina.Dto.usuario;

import com.deusleyDev.apiOficina.enuns.PerfilUsuario;

public record UsuarioResponse(

        Long id,
        String nome,
        String email,
        PerfilUsuario perfil
) {
}
