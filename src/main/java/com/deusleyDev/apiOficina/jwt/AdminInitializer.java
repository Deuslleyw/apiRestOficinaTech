package com.deusleyDev.apiOficina.jwt;

import com.deusleyDev.apiOficina.domain.Usuario;
import com.deusleyDev.apiOficina.enuns.PerfilUsuario;
import com.deusleyDev.apiOficina.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.senha}")
    private String adminSenha;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByEmail(adminEmail).isEmpty()) {
            var admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail(adminEmail);
            admin.setSenha(passwordEncoder.encode(adminSenha));
            admin.setPerfil(PerfilUsuario.ADMIN);
            usuarioRepository.save(admin);
            System.out.println("Admin criado com sucesso!");
        }

    }
}
