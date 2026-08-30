package com.deusleyDev.apiOficina.Dto.ordemServico;

import com.deusleyDev.apiOficina.enuns.StatusOrdem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OrdemServicoRequest(

        @NotBlank(message = "Preencha este campo com as descrições/especificações")
        @Size(min = 3, max = 700)
        String descricao,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "valor de ve ser maior que zero")
        Double valor,

        @NotNull(message = "Status Obrigatório")
        StatusOrdem status,

        @NotNull(message = "Cliente é obrigatório")
        Long clienteId,

        @NotNull(message = "veiculo é obrigatório")
        Long veiculoId

) {
}
