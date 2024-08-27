package br.com.mobiauto.mobiauto_server.core.entity;

import br.com.mobiauto.mobiauto_server.core.enums.Status;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class Oportunity {

    private Status status;
    private String reasonConclusion;
    private LocalDate assignmentDate;
    private LocalDate conclusionDate;
    private Client client;
    private Vehicle vehicle;
    private User user;
    private CarDealer carDealer;
}
