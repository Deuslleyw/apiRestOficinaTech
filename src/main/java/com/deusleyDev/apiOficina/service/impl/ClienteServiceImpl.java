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

        var list  = clienteRepository.findAll();
        var response =  list.stream().map(clienteMapper::toResponse).toList();

        return response;
    }

    @Override
    public ClienteResponse findById(Long id) {

        var cliente  = clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente não encontrado! "));
        return clienteMapper.toResponse(cliente);
    }

    @Override
    public ClienteResponse update(Long id, ClienteRequest clienteRequest) {

        var cliente  =  clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Erro ao atualizar, cliente não encontrado!"));
        cliente.setNome(clienteRequest.nome());
        cliente.setTelefone(clienteRequest.telefone());
        cliente.setEmail(clienteRequest.email());

        var clienteAtualizado = clienteRepository.save(cliente);

        return clienteMapper.toResponse(clienteAtualizado);
    }

    @Override
    public void delete(Long id) {
        var cliente  = clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Erro ao deletar, Cliente não encontrado"));
        clienteRepository.delete(cliente);

    }
}
