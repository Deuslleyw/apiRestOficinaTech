package com.deusleyDev.apiOficina.controller;

import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoRequest;
import com.deusleyDev.apiOficina.Dto.veiculo.VeiculoResponse;
import com.deusleyDev.apiOficina.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/veiculo")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<VeiculoResponse> criar(@RequestBody VeiculoRequest request) {
        var response = veiculoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> listar() {
        var lista = veiculoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> findByid(@PathVariable Long id) {
        var response = veiculoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponse> update(@PathVariable Long id, @RequestBody VeiculoRequest request) {
        var response = veiculoService.update(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
