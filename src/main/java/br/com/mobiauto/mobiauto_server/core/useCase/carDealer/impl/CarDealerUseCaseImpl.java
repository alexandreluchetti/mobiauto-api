package br.com.mobiauto.mobiauto_server.core.useCase.carDealer.impl;

import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerUseCase;
import br.com.mobiauto.mobiauto_server.entrypoint.dto.CreateCarDealerDto;

public class CarDealerUseCaseImpl implements CarDealerUseCase {

    private final CarDealerRepository repository;

    public CarDealerUseCaseImpl(CarDealerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createCarDealer(CreateCarDealerDto carDealerDto) {
        repository.createCarDealer(carDealerDto);
    }

}
