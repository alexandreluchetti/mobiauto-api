package br.com.mobiauto.mobiauto_server.core.useCase.oportunity;

import br.com.mobiauto.mobiauto_server.core.entity.oportunity.CreateOportunityRequest;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.OportunityEntity;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.updateOportunity.UpdateOportunityDto;

import java.util.List;

public interface OportunityRepository {

    List<OportunityEntity> getAllOportunities();

    OportunityEntity getOportunitiesById(Long id);

    void createOportunity(CreateOportunityRequest oportunityRequest);

    void updateOportunity(Long id, UpdateOportunityDto oportunityDto);

    void deleteOportunity(Long id);

    List<OportunityEntity> getOportunitiesByCarDealer(Long carDealerId);
}
