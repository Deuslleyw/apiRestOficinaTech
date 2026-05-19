package com.deusleyDev.apiOficina.mapper;

import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoRequest;
import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoResponse;
import com.deusleyDev.apiOficina.domain.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = ClienteMapper.class)
public interface VeiculoMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "clienteNome", source = "cliente.nome")
    VeiculoResponse toResponse(Veiculo veiculo);

    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "clienteById")
    Veiculo toEntity(VeiculoRequest request);

    @Named("veiculoById")
    default Veiculo toVeiculo(Long id) {
        if (id == null) return null;
        Veiculo veiculo = new Veiculo();
        veiculo.setId(id);
        return veiculo;


    }
}