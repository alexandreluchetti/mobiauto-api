package br.com.mobiauto.mobiauto_server.dataprovider.user.entity;

import br.com.mobiauto.mobiauto_server.core.enums.RoleEnum;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.CarDealerEntity;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.AddressDto;

public record UserEntity(

        Long id,
        String name,
        String email,
        RoleEnum roleEnum,
        CarDealerEntity carDealer,
        AddressDto addressDto
) {



}
