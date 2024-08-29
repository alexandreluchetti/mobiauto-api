package br.com.mobiauto.mobiauto_server.entrypoint.user.dto;

import br.com.mobiauto.mobiauto_server.core.entity.user.CreateUser;
import br.com.mobiauto.mobiauto_server.core.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

public record CreateUserDto2(

        @JsonProperty(value = "nome")
        String name,

        @Email
        String email,

        @JsonProperty(value = "senha")
        String password,

        @JsonProperty(value = "cargo")
        RoleEnum roleEnum,

        @JsonProperty(value = "revenda_id")
        Long carDealerId,

        @JsonProperty(value = "endereco_id")
        Long addressId
) {

    public CreateUser toObject() {
        return new CreateUser(name, email, password, roleEnum, carDealerId, addressId);
    }

}
