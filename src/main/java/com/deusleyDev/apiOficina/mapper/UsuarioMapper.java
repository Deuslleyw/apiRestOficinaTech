package com.deusleyDev.apiOficina.mapper;

import com.deusleyDev.apiOficina.Dto.usuario.UsuarioRequest;
import com.deusleyDev.apiOficina.Dto.usuario.UsuarioResponse;
import com.deusleyDev.apiOficina.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "senha", ignore = true)
    Usuario toEntity(UsuarioRequest request);

    UsuarioResponse toResponse(Usuario usuario);
}
