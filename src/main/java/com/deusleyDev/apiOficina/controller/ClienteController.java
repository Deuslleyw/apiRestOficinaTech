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
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findByid(@PathVariable Long id){
        var clienteIdResponse = clienteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clienteIdResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@PathVariable Long id, @RequestBody ClienteRequest request){
        var clienteUpResponse = clienteService.update(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteUpResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
}
}
