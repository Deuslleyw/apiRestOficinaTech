package com.deusleyDev.apiOficina.service.impl;

import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoRequest;
import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoResponse;
import com.deusleyDev.apiOficina.enuns.StatusOrdem;
import com.deusleyDev.apiOficina.mapper.OrderMapper;
import com.deusleyDev.apiOficina.repositories.OrdemServicoRepository;
import com.deusleyDev.apiOficina.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrdemServicoServiceImpl implements OrdemServicoService {


    private final OrdemServicoRepository ordemServicoRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrdemServicoResponse create(OrdemServicoRequest request) {

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
                .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada!"));
        return orderMapper.toResponse(ordemServico);
    }

    @Override
    public OrdemServicoResponse update(Long id, OrdemServicoRequest request) {

        var ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao atualizar, ordem de serviço não encontrada!"));
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
                .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada!"));
        ordemServico.setStatus(StatusOrdem.CANCELADA);
        ordemServicoRepository.save(ordemServico);
    }

    }
