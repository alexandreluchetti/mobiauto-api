package br.com.mobiauto.mobiauto_server.core.entity;

import br.com.mobiauto.mobiauto_server.core.enums.Position;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {

    private String name;
    private String email;
    private String password;
    private Position position;
    private CarDealer carDealer;
    private Address address;
}
