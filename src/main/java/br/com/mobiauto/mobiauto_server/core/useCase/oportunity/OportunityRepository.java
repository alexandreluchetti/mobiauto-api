package br.com.mobiauto.mobiauto_server.core.useCase.oportunity;

import br.com.mobiauto.mobiauto_server.core.entity.oportunity.CreateOportunityRequest;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.OportunityEntity;

import java.util.List;

public interface OportunityRepository {

    List<OportunityEntity> getAllOportunities();

    OportunityEntity getOportunitiesById(Long id);

    void createOportunity(CreateOportunityRequest oportunityRequest);
}
