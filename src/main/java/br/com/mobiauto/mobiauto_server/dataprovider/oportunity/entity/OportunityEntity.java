package br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity;

import br.com.mobiauto.mobiauto_server.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OportunityEntity {

    private Status status;
    private String reasonConclusion;
    private LocalDate assignmentDate;
    private LocalDate conclusionDate;
    private ClientEntity client;
    private VehicleEntity vehicle;
    private UserEntity user;
    private CarDealerEntity carDealer;

}
