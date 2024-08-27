package br.com.mobiauto.mobiauto_server.core.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Address {

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
}
