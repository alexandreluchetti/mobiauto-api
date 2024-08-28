package br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity;

import br.com.mobiauto.mobiauto_server.core.enums.Status;
import br.com.mobiauto.mobiauto_server.core.shared.Utils;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.getOportunities.OportunityResponseDto;
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

    public OportunityResponseDto toDto() {
        return new OportunityResponseDto(
                this.status.getValue(),
                this.reasonConclusion,
                Utils.format(this.assignmentDate),
                Utils.format(this.conclusionDate),
                this.client,
                this.vehicle,
                this.user.toDto(),
                this.carDealer
        );
    }

}
