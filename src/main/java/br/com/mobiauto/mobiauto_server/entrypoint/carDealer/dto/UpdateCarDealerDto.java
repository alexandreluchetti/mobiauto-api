package br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCarDealerDto(

        @JsonProperty("razao_social")
        String companyName,

        @JsonProperty("endereco")
        AddressDto addressDto

) {
}
