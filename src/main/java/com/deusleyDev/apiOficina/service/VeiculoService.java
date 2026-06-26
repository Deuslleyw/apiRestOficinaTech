package com.deusleyDev.apiOficina.service;

import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoRequest;
import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoResponse;

import java.util.List;

public interface VeiculoService {

    VeiculoResponse create (VeiculoRequest veiculoRequest);
    List<VeiculoResponse> findAll();
    VeiculoResponse findById(Long id);
    VeiculoResponse update(Long id, VeiculoRequest request);
    void delete(Long id);

}
