package br.com.mobiauto.mobiauto_server.dataprovider.repository.entity;

public record UserDto(

        Long id,
        String name,
        String email,
        String password,
        String position,
        Long carDealerId,
        Long addressId

) {
}
