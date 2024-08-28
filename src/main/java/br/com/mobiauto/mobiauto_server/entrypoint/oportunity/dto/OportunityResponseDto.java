package br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto;

import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.CarDealerEntity;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.ClientEntity;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.VehicleEntity;

public record OportunityResponseDto(

        String status,
        String reasonConclusion,
        String assignmentDate,
        String conclusionDate,
        ClientEntity client,
        VehicleEntity vehicle,
        UserResponseDto user,
        CarDealerEntity carDealer

) {
}
