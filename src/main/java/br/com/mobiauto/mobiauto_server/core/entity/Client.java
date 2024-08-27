package br.com.mobiauto.mobiauto_server.core.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {

    private String name;
    private String email;
    private String cellphone;
    private Address address;
}
