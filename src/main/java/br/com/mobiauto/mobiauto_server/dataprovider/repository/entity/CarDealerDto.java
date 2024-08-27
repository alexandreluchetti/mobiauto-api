package br.com.mobiauto.mobiauto_server.dataprovider.repository.entity;

public record CarDealerDto(

        Long id,
        String cnpj,
        String companyName,
        Long addressId

) {
}
