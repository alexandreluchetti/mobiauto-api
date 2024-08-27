package br.com.mobiauto.mobiauto_server.core.enums;

import lombok.Getter;

@Getter
public enum Status {

    NEW("NOVO"),
    IN_SERVICE("EM_ATENDIMENTO"),
    COMPLETED("CONCLUIDO");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public static Status from(String value){
        for (Status status : Status.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }
}
