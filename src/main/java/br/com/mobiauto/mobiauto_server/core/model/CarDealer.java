package br.com.mobiauto.mobiauto_server.core.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CarDealer {

    private String cnpj;
    private String companyName;
    private Address address;
    private List<User> users;
    private List<Oportunity> oportunities;
}
