package com.deusleyDev.apiOficina.controller;

import com.deusleyDev.apiOficina.Dto.cliente.ClienteRequest;
import com.deusleyDev.apiOficina.Dto.cliente.ClienteResponse;
import com.deusleyDev.apiOficina.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {


    private final ClienteService clienteService;


    @PostMapping
    public ResponseEntity<ClienteResponse> criar (@RequestBody ClienteRequest request){
        var clienteResponse = clienteService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar(){
        List<ClienteResponse> lista = clienteService.findAll();
        return ResponseEntity.ok(lista);
    }




}
