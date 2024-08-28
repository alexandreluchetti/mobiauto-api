package br.com.mobiauto.mobiauto_server.core.useCase.carDealer;

import br.com.mobiauto.mobiauto_server.dataprovider.carDealer.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.CreateCarDealerDto;

import java.util.List;

public interface CarDealerUseCase {

    void createCarDealer(CreateCarDealerDto carDealerDto);

    CarDealerResponseDto getCarDealerByCnpj(String cnpj);

    List<CarDealerResponseDto> getAllCarDealers();
}
