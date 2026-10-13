package com.deusleyDev.apiOficina.service;

import com.deusleyDev.apiOficina.Dto.usuario.UsuarioRequest;
import com.deusleyDev.apiOficina.Dto.usuario.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

   UsuarioResponse create (UsuarioRequest usuarioRequest);

   List<UsuarioResponse> findAll ();



}
