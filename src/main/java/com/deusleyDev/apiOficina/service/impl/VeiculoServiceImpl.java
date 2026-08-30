package com.deusleyDev.apiOficina.service.impl;

import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoRequest;
import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoResponse;
import com.deusleyDev.apiOficina.exceptions.DataIntegrityViolationException;
import com.deusleyDev.apiOficina.exceptions.VeiculoNotFoundException;
import com.deusleyDev.apiOficina.mapper.VeiculoMapper;
import com.deusleyDev.apiOficina.repositories.ClienteRepository;
import com.deusleyDev.apiOficina.repositories.OrdemServicoRepository;
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
    private final ClienteRepository clienteRepository;
    private final OrdemServicoRepository ordemServicoRepository;


    @Override
    public VeiculoResponse create(VeiculoRequest veiculoRequest) {

        clienteRepository.findById(veiculoRequest.clienteId())
                .orElseThrow(()-> new DataIntegrityViolationException(
                        "Cliente não encontrado como o id: " + veiculoRequest.clienteId()));

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
                .orElseThrow(() -> new VeiculoNotFoundException("Veículo não encontrado!"));
        return veiculoMapper.toResponse(veiculo);
    }

    @Override
    public VeiculoResponse update(Long id, VeiculoRequest request) {
        var veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Erro ao atualizar, veículo não encontrado!"));
        clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new DataIntegrityViolationException("Cliente não encontrado com id: "
                                                                       + request.clienteId()));
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
                .orElseThrow(() -> new VeiculoNotFoundException("Erro ao deletar, veículo não encontrado!"));

        if (ordemServicoRepository.existsByVeiculoId(id))
            throw new DataIntegrityViolationException(
                    "Veículo possui ordens de serviço vinculadas e não pode ser excluído");

        veiculoRepository.delete(veiculo);

    }
}
