package br.com.mobiauto.mobiauto_server.dataprovider.entity;

public record CarDealerDto(

        Long id,
        String cnpj,
        String companyName,
        AddressDto address

) {
}
