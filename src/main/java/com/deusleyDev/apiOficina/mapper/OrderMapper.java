package com.deusleyDev.apiOficina.mapper;

import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoRequest;
import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoResponse;
import com.deusleyDev.apiOficina.domain.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, VeiculoMapper.class})
public interface OrderMapper {

    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "clienteById")
    @Mapping(target = "veiculo", source = "veiculoId", qualifiedByName = "veiculoById")
    OrdemServico toEntity(OrdemServicoRequest request);

    OrdemServicoResponse toResponse(OrdemServico ordemServico);





}
