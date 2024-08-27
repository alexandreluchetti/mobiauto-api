package br.com.mobiauto.mobiauto_server.core.useCase.carDealer;

import br.com.mobiauto.mobiauto_server.entrypoint.dto.CreateCarDealerDto;

public interface CarDealerRepository {

    void createCarDealer(CreateCarDealerDto carDealerDto);
}
