package com.deusleyDev.apiOficina.Dto.login;

public record LoginResponse(

        String token,
        String tipo,
        String nome,
        String perfil

) {
}
