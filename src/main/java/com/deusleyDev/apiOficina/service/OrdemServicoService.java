package com.deusleyDev.apiOficina.service;

import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoRequest;
import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoResponse;

import java.util.List;

public interface OrdemServicoService {

    OrdemServicoResponse create(OrdemServicoRequest request);
    List<OrdemServicoResponse> findAll();
    OrdemServicoResponse findById(Long id);
    OrdemServicoResponse update(Long id, OrdemServicoRequest request);
    void cancelar(Long id);
}
