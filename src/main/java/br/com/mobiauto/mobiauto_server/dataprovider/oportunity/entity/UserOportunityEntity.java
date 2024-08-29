package br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity;

import br.com.mobiauto.mobiauto_server.core.enums.RoleEnum;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.getOportunities.UserResponseDto;

public record UserOportunityEntity(

        String name,
        String email,
        RoleEnum roleEnum

) {

    public UserResponseDto toDto() {
        return new UserResponseDto(
                this.name,
                this.email,
                this.roleEnum.getValue()
        );
    }

}
