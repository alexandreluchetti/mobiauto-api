package br.com.mobiauto.mobiauto_server.core.enums;

import lombok.Getter;

@Getter
public enum Position {

    ADMINISTRATOR("ADMINISTRADOR"),
    OWNER("PROPRIETARIO"),
    MANAGER("GERENTE"),
    ASSISTANT("ASSISTENTE");

    private String value;

    Position(String value) {
        this.value = value;
    }

    public static Position from(String value){
        for(Position position : Position.values()){
            if (position.value.equals(value)) {
                return position;
            }
        }
        return null;
    }
}
