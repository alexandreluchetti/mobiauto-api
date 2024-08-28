package br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity;

import br.com.mobiauto.mobiauto_server.core.enums.Position;

public record UserEntity(

        String name,
        String email,
        Position position

) {
}
