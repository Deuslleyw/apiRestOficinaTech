package com.deusleyDev.apiOficina.controller;

import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoRequest;
import com.deusleyDev.apiOficina.Dto.ordemServico.OrdemServicoResponse;
import com.deusleyDev.apiOficina.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ordem-servico")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;



    @PostMapping
    public ResponseEntity<OrdemServicoResponse> criar(@RequestBody OrdemServicoRequest request) {
        var response = ordemServicoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoResponse>> listar() {
        var lista = ordemServicoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> findByid(@PathVariable Long id) {
        var response = ordemServicoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> update(@PathVariable Long id, @RequestBody OrdemServicoRequest request) {
        var response = ordemServicoService.update(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ordemServicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
