package br.com.mobiauto.mobiauto_server.core.entity;

public enum Cargo {
    PROPRIETARIO, GERENTE, ASSISTENTE, ADMINISTRADOR;

    public boolean isProprietarioOuGerente(){
        return this == PROPRIETARIO || this == GERENTE;
    }

    public boolean isProprietario(){
        return this == PROPRIETARIO;
    }

    public boolean isGerente(){
        return this == GERENTE;
    }

    public boolean isAssistente(){
        return this == ASSISTENTE;
    }

    public boolean isAdministrador(){
        return this == ADMINISTRADOR;
    }
}