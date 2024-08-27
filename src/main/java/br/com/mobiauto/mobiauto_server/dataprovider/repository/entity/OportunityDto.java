package br.com.mobiauto.mobiauto_server.dataprovider.repository.entity;

import java.sql.Date;

public record OportunityDto(

        Long id,
        String status,
        String reasonConclusion,
        Date assignmentDate,
        Date conclusionDate,
        Long clientId,
        Long vehicleId,
        Long userId,
        Long carDealerId

) {
}
