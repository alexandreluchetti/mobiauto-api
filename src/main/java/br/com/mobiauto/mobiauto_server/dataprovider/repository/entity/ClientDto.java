package br.com.mobiauto.mobiauto_server.dataprovider.repository.entity;

public record ClientDto(

        Long id,
        String nome,
        String email,
        String cellphone,
        Long addressId

) {
}
