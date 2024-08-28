package br.com.mobiauto.mobiauto_server.core.useCase.oportunity.impl;

import br.com.mobiauto.mobiauto_server.configuration.exception.OperationException;
import br.com.mobiauto.mobiauto_server.core.entity.DefaultResponse;
import br.com.mobiauto.mobiauto_server.core.entity.oportunity.CreateOportunityRequest;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityUseCase;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.OportunityEntity;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.updateOportunity.UpdateOportunityDto;

import java.util.List;

public class OportunityUseCaseImpl implements OportunityUseCase {

    private final OportunityRepository repository;

    public OportunityUseCaseImpl(OportunityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createOportunity(CreateOportunityRequest oportunityRequest) {
        repository.createOportunity(oportunityRequest);
    }

    @Override
    public List<OportunityEntity> getAllOportunities() {
        return repository.getAllOportunities();
    }

    @Override
    public OportunityEntity getOportunityById(Long id) {
        return repository.getOportunitiesById(id);
    }

    @Override
    public OportunityEntity updateOportunity(Long id, UpdateOportunityDto updateOportunityDto) {
        repository.updateOportunity(id, updateOportunityDto);
        return repository.getOportunitiesById(id);
    }

    @Override
    public DefaultResponse deleteOportunity(Long id) {
        OportunityEntity oportunity = repository.getOportunitiesById(id);
        if (oportunity.getActive()) {
            repository.deleteOportunity(id);
            return DefaultResponse.success();
        } else {
            return new DefaultResponse(500, "A oportunidade ja foi deletada!");
        }
    }

    @Override
    public List<OportunityEntity> getOportunitiesByCarDealer(Long carDealerId) {
        List<OportunityEntity> oportunities = repository.getOportunitiesByCarDealer(carDealerId);
        if (oportunities.isEmpty()) throw new OperationException("Essa revenda nao possui nenhuma oportunidade");
        return oportunities;
    }
}
