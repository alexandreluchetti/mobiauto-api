package br.com.mobiauto.mobiauto_server.core.useCase.carDealer.impl;

import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerUseCase;
import br.com.mobiauto.mobiauto_server.dataprovider.repository.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.dto.CreateCarDealerDto;

import java.util.List;

public class CarDealerUseCaseImpl implements CarDealerUseCase {

    private final CarDealerRepository repository;

    public CarDealerUseCaseImpl(CarDealerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CarDealerResponseDto createCarDealer(CreateCarDealerDto carDealerDto) {
        return repository.createCarDealer(carDealerDto);
    }

    @Override
    public CarDealerResponseDto getCarDealerByCnpj(String cnpj) {
        return repository.getCarDealerByCnpj(cnpj);
    }

    @Override
    public List<CarDealerResponseDto> getAllCarDealers() {
        return repository.getAllCarDealers();
    }

}
