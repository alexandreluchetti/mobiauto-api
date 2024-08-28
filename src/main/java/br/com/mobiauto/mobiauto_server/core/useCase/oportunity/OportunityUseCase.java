package br.com.mobiauto.mobiauto_server.core.useCase.oportunity;

import br.com.mobiauto.mobiauto_server.core.entity.DefaultResponse;
import br.com.mobiauto.mobiauto_server.core.entity.oportunity.CreateOportunityRequest;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.OportunityEntity;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.updateOportunity.UpdateOportunityDto;

import java.util.List;

public interface OportunityUseCase {

    void createOportunity(CreateOportunityRequest oportunityRequest);

    List<OportunityEntity> getAllOportunities();

    OportunityEntity getOportunityById(Long id);

    OportunityEntity updateOportunity(Long id, UpdateOportunityDto updateOportunityDto);

    DefaultResponse deleteOportunity(Long id);

    List<OportunityEntity> getOportunitiesByCarDealer(Long carDealerId);
}
