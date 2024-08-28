package br.com.mobiauto.mobiauto_server.core.entity.oportunity;

import br.com.mobiauto.mobiauto_server.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateOportunityRequest {

    private Status status;
    private LocalDate assignmentDate;
    private Long clientId;
    private Long vehicleId;
    private Long userId;
    private Long carDealerId;
}
