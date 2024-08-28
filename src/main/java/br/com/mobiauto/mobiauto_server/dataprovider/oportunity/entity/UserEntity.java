package br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity;

import br.com.mobiauto.mobiauto_server.core.enums.Position;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.getOportunities.UserResponseDto;

public record UserEntity(

        String name,
        String email,
        Position position

) {

    public UserResponseDto toDto() {
        return new UserResponseDto(
                this.name,
                this.email,
                this.position.getValue()
        );
    }

}
