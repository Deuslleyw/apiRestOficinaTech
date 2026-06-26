package com.deusleyDev.apiOficina.service.impl;

import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoRequest;
import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoResponse;
import com.deusleyDev.apiOficina.mapper.VeiculoMapper;
import com.deusleyDev.apiOficina.repositories.VeiculoRepository;
import com.deusleyDev.apiOficina.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VeiculoServiceImpl  implements VeiculoService {

    private final VeiculoMapper veiculoMapper;
    private final VeiculoRepository veiculoRepository;


    @Override
    public VeiculoResponse create(VeiculoRequest veiculoRequest) {

        var veiculo = veiculoMapper.toEntity(veiculoRequest);
        var veiculoSalvo = veiculoRepository.save(veiculo);
        return veiculoMapper.toResponse(veiculoSalvo);

    }

    @Override
    public List<VeiculoResponse> findAll() {
        var list = veiculoRepository.findAll();
        return list.stream().map(veiculoMapper::toResponse).toList();
    }

    @Override
    public VeiculoResponse findById(Long id) {
        var veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));
        return veiculoMapper.toResponse(veiculo);
    }

    @Override
    public VeiculoResponse update(Long id, VeiculoRequest request) {
        var veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao atualizar, veículo não encontrado!"));
        veiculo.setMarca(request.marca());
        veiculo.setModelo(request.modelo());
        veiculo.setPlaca(request.placa());
        veiculo.setAno(request.ano());
        var veiculoAtualizado = veiculoRepository.save(veiculo);
        return veiculoMapper.toResponse(veiculoAtualizado);
    }

    @Override
    public void delete(Long id) {
        var veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao deletar, veículo não encontrado!"));
        veiculoRepository.delete(veiculo);

    }
}
