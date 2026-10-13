package com.deusleyDev.apiOficina.service.impl;

import com.deusleyDev.apiOficina.Dto.usuario.UsuarioRequest;
import com.deusleyDev.apiOficina.Dto.usuario.UsuarioResponse;
import com.deusleyDev.apiOficina.exceptions.DataIntegrityViolationException;
import com.deusleyDev.apiOficina.mapper.UsuarioMapper;
import com.deusleyDev.apiOficina.repositories.UsuarioRepository;
import com.deusleyDev.apiOficina.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UsuarioResponse create(UsuarioRequest usuarioRequest) {

        if (usuarioRepository.findByEmail(usuarioRequest.email()).isPresent()) {
            throw new DataIntegrityViolationException("Email já cadastrado: " + usuarioRequest.email());
        }
        var usuario = usuarioMapper.toEntity(usuarioRequest);
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.senha()));
        var usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuarioSalvo);

    }

    @Override
    public List<UsuarioResponse> findAll() {
        var list = usuarioRepository.findAll();
        return list.stream().map(usuarioMapper::toResponse).toList();
    }
}
