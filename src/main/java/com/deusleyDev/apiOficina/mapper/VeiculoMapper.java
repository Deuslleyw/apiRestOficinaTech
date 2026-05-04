package com.deusleyDev.apiOficina.mapper;

import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoRequest;
import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoResponse;
import com.deusleyDev.apiOficina.domain.Cliente;
import com.deusleyDev.apiOficina.domain.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "clienteNome", source = "cliente.nome")
    VeiculoResponse toResponse(Veiculo veiculo);

    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "toCliente")
    Veiculo toEntity(VeiculoRequest request);

    @Named("toCliente")
    default Cliente toCliente(Long id) {
        if (id == null) return null;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }

}
