package br.com.mobiauto.mobiauto_server.core.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleEnum implements GrantedAuthority {

    ADMINISTRADOR("ADMINISTRADOR"),
    PROPRIETARIO(""),
    GERENTE("GERENTE"),
    ASSISTENTE("ASSISTENTE");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public static RoleEnum from(String value){
        for(RoleEnum roleEnum : RoleEnum.values()){
            if (roleEnum.value.equals(value)) {
                return roleEnum;
            }
        }
        return null;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.getValue();
    }
}
