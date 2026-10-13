package com.deusleyDev.apiOficina.controller;

import com.deusleyDev.apiOficina.Dto.usuario.UsuarioRequest;
import com.deusleyDev.apiOficina.Dto.usuario.UsuarioResponse;
import com.deusleyDev.apiOficina.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar (@RequestBody @Valid UsuarioRequest request){
        var response = usuarioService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(){
        var lista = usuarioService.findAll();
        return ResponseEntity.ok(lista);
    }


}
