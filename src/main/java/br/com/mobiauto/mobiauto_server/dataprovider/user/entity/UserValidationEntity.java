package br.com.mobiauto.mobiauto_server.dataprovider.user.entity;

import br.com.mobiauto.mobiauto_server.core.enums.RoleEnum;

public record UserValidationEntity(

        String name,
        String email,
        String password,
        RoleEnum role

) {
}
