package com.deusleyDev.apiOficina.service.impl;

import com.deusleyDev.apiOficina.Dto.cliente.ClienteRequest;
import com.deusleyDev.apiOficina.Dto.cliente.ClienteResponse;
import com.deusleyDev.apiOficina.mapper.ClienteMapper;
import com.deusleyDev.apiOficina.repositories.ClienteRepository;
import com.deusleyDev.apiOficina.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {


    private final ClienteRepository clienteRepository;
    private  final ClienteMapper clienteMapper;


    @Override
    public ClienteResponse create(ClienteRequest clienteRequest) {

        var cliente = clienteMapper.toEntity(clienteRequest);
        var clienteCreated = clienteRepository.save(cliente);
        var response = clienteMapper.toResponse(clienteCreated);

        return response;
    }

    @Override
    public List<ClienteResponse> findAll() {
        return List.of();
    }

    @Override
    public ClienteResponse findById(Long id) {
        return null;
    }

    @Override
    public ClienteResponse update(Long id, ClienteRequest clienteRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
