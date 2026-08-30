package com.deusleyDev.apiOficina.repositories;

import com.deusleyDev.apiOficina.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    boolean existsByClienteId(Long clienteId);
    boolean existsByVeiculoId(Long veiculoId);

}
