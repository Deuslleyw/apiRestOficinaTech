package com.deusleyDev.apiOficina.service.impl;

import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoRequest;
import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoResponse;
import com.deusleyDev.apiOficina.enuns.StatusOrdem;
import com.deusleyDev.apiOficina.exceptions.DataIntegrityViolationException;
import com.deusleyDev.apiOficina.exceptions.OrdenServicoNotFoundException;
import com.deusleyDev.apiOficina.mapper.OrderMapper;
import com.deusleyDev.apiOficina.repositories.ClienteRepository;
import com.deusleyDev.apiOficina.repositories.OrdemServicoRepository;
import com.deusleyDev.apiOficina.repositories.VeiculoRepository;
import com.deusleyDev.apiOficina.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrdemServicoServiceImpl implements OrdemServicoService {


    private final OrdemServicoRepository ordemServicoRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrdemServicoResponse create(OrdemServicoRequest request) {

        clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new DataIntegrityViolationException("Cliente não encontrado com id: "
                        + request.clienteId()));
       var veiculo =  veiculoRepository.findById(request.veiculoId())
                .orElseThrow(() -> new DataIntegrityViolationException("Veículo não encontrado com id: "
                        + request.veiculoId()));

        if (!veiculo.getCliente().getId().equals(request.clienteId())) {
            throw new DataIntegrityViolationException("O veículo informado não pertence ao cliente informado.");
        }


        var ordemServico = orderMapper.toEntity(request);
        var ordemSalva = ordemServicoRepository.save(ordemServico);
        return orderMapper.toResponse(ordemSalva);
    }

    @Override
    public List<OrdemServicoResponse> findAll() {

        var list = ordemServicoRepository.findAll();
        return list.stream().map(orderMapper::toResponse).toList();
    }

    @Override
    public OrdemServicoResponse findById(Long id) {
        var ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new OrdenServicoNotFoundException("Ordem de serviço não encontrada!"));
        return orderMapper.toResponse(ordemServico);
    }

    @Override
    public OrdemServicoResponse update(Long id, OrdemServicoRequest request) {

        var ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new OrdenServicoNotFoundException("Erro ao atualizar, ordem de serviço não encontrada!"));

        clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new DataIntegrityViolationException("Cliente não encontrado com id: "
                        + request.clienteId()));
      var veiculo =  veiculoRepository.findById(request.veiculoId())
                .orElseThrow(() -> new DataIntegrityViolationException("Veículo não encontrado com id: "
                        + request.veiculoId()));
        if (!veiculo.getCliente().getId().equals(request.clienteId())) {
            throw new DataIntegrityViolationException("O veículo informado não pertence ao cliente informado.");
        }

        ordemServico.setDescricao(request.descricao());
        ordemServico.setValor(request.valor());
        ordemServico.setStatus(request.status());
        if (request.status() == StatusOrdem.CONCLUIDA) {
            ordemServico.setDataFechamento(LocalDateTime.now());
        }
        var ordemAtualizada = ordemServicoRepository.save(ordemServico);
        return orderMapper.toResponse(ordemAtualizada);

    }

    @Override
    public void cancelar(Long id) {
        var ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new OrdenServicoNotFoundException("Ordem de serviço não encontrada!"));
        ordemServico.setStatus(StatusOrdem.CANCELADA);
        ordemServicoRepository.save(ordemServico);
    }

}
