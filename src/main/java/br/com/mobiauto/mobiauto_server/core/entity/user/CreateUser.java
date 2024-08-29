package br.com.mobiauto.mobiauto_server.core.entity.user;

import br.com.mobiauto.mobiauto_server.core.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUser {

    private String name;
    private String email;
    private String password;
    private RoleEnum roleEnum;
    private Long carDealerId;
    private Long addressId;
}
