package com.deusleyDev.apiOficina.mapper;

import com.deusleyDev.apiOficina.Dto.cliente.ClienteRequest;
import com.deusleyDev.apiOficina.Dto.cliente.ClienteResponse;
import com.deusleyDev.apiOficina.domain.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteRequest request);

    ClienteResponse toResponse(Cliente cliente);


}
