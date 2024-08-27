package br.com.mobiauto.mobiauto_server.core.useCase.carDealer;

import br.com.mobiauto.mobiauto_server.dataprovider.repository.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.dto.CreateCarDealerDto;

import java.util.List;

public interface CarDealerUseCase {

    CarDealerResponseDto createCarDealer(CreateCarDealerDto carDealerDto);

    CarDealerResponseDto getCarDealerByCnpj(String cnpj);

    List<CarDealerResponseDto> getAllCarDealers();
}
