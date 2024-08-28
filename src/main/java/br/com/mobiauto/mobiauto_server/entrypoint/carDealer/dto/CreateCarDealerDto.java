package br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCarDealerDto(

        @NotBlank
        @Schema(example = "20800123000100")
        @Size(min = 14, max = 14, message = "O CNPJ deve ter 14 caracteres (apenas numeros).")
        String cnpj,

        @NotBlank
        @JsonProperty("razao_social")
        @Schema(example = "Nome da Sua Empresa LTDA")
        @Size(max = 255, message = "A razao_social nao pode ser vazia.")
        String companyName,

        @Valid
        @JsonProperty("endereco")
        AddressDto address

) {
}
