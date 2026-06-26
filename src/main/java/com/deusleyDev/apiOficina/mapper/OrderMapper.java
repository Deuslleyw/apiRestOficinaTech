package com.deusleyDev.apiOficina.mapper;

import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoRequest;
import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoResponse;
import com.deusleyDev.apiOficina.domain.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, VeiculoMapper.class})
public interface OrderMapper {

    @Mapping(source = "clienteId", target = "cliente", qualifiedByName = "clienteById")
    @Mapping(source = "veiculoId", target = "veiculo", qualifiedByName = "veiculoById")
    @Mapping(target = "dataAbertura", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "dataFechamento", ignore = true)
    OrdemServico toEntity(OrdemServicoRequest request);

    OrdemServicoResponse toResponse(OrdemServico ordemServico);
}