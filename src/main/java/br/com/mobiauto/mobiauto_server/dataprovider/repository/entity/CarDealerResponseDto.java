package br.com.mobiauto.mobiauto_server.dataprovider.repository.entity;

import br.com.mobiauto.mobiauto_server.entrypoint.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CarDealerResponseDto(

        Long id,

        String cnpj,

        @JsonProperty("razao_social")
        String companyName,

        @JsonProperty("endereco")
        AddressDto address

) {
}
