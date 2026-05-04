package com.deusleyDev.apiOficina.mapper;

import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoRequest;
import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoResponse;
import com.deusleyDev.apiOficina.domain.Cliente;
import com.deusleyDev.apiOficina.domain.OrdemServico;
import com.deusleyDev.apiOficina.domain.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, VeiculoMapper.class})
public interface OrderMapper {

    @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "toCliente")
    @Mapping(target = "veiculo", source = "veiculoId", qualifiedByName = "toVeiculo")
    OrdemServico toEntity(OrdemServicoRequest request);

    OrdemServicoResponse toResponse(OrdemServico ordemServico);


    @Named("toCliente")
    default Cliente toCliente(Long id) {
        if (id == null) return null;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }

    @Named("toVeiculo")
    default Veiculo toVeiculo(Long id) {
        if (id == null) return null;
        Veiculo veiculo = new Veiculo();
        veiculo.setId(id);
        return veiculo;
    }

}
