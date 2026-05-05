package com.deusleyDev.apiOficina.service;

import com.deusleyDev.apiOficina.Dto.cliente.ClienteRequest;
import com.deusleyDev.apiOficina.Dto.cliente.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse create (ClienteRequest clienteRequest);

    List<ClienteResponse> findAll();

    ClienteResponse findById(Long id);

    ClienteResponse update (Long id, ClienteRequest clienteRequest);

    void delete (Long id);

}
