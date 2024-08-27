package br.com.mobiauto.mobiauto_server.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressRequestDto(

        @NotBlank
        @Schema(example = "Horacio Pessine")
        @JsonProperty("rua")
        String street,

        @Schema(example = "155")
        @JsonProperty("numero")
        String number,

        @Schema(example = "Ap 1604")
        @JsonProperty("complemento")
        String complement,

        @Schema(example = "Nova Cidade")
        @JsonProperty("bairro")
        String neighborhood,

        @Schema(example = "Sao Paulo")
        @JsonProperty("cidade")
        String city,

        @NotBlank
        @Schema(example = "SP")
        @Size(min = 2, max = 2, message = "Digite a sigla do estado (ex: SP).")
        @JsonProperty("estado")
        String state,

        @NotBlank
        @Schema(example = "14026555")
        @Size(min = 8, max = 8, message = "O cep deve ter 8 caracteres (apenas numeros).")
        @JsonProperty("cep")
        String zipCode

) {
}
