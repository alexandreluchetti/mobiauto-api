package br.com.mobiauto.mobiauto_server.core.useCase.oportunity.impl;

import br.com.mobiauto.mobiauto_server.core.entity.oportunity.CreateOportunityRequest;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityUseCase;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.OportunityEntity;

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
}
