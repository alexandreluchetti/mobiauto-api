package br.com.mobiauto.mobiauto_server.dataprovider.repository.entity;

public record AddressDto(

        Long id,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode

) {
}
