package com.deusleyDev.apiOficina.jwt;

import com.deusleyDev.apiOficina.domain.Usuario;
import com.deusleyDev.apiOficina.enuns.PerfilUsuario;
import com.deusleyDev.apiOficina.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByEmail("admin@oficina.com").isEmpty()) {
            var admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@oficina.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setPerfil(PerfilUsuario.ADMIN);
            usuarioRepository.save(admin);
            System.out.println("Admin criado: admin@oficina.com / admin123");
        }

    }
}
