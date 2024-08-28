package br.com.mobiauto.mobiauto_server.dataprovider.carDealer.entity;

import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CarDealerResponseDto(

        Long id,

        String cnpj,

        @JsonProperty("razao_social")
        String companyName,

        @JsonProperty("ativa")
        Boolean active,

        @JsonProperty("endereco")
        AddressDto address

) {
}
