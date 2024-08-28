package br.com.mobiauto.mobiauto_server.core.useCase.carDealer;

import br.com.mobiauto.mobiauto_server.core.entity.DefaultResponse;
import br.com.mobiauto.mobiauto_server.dataprovider.carDealer.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.CreateCarDealerDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.UpdateCarDealerDto;

import java.util.List;

public interface CarDealerUseCase {

    void createCarDealer(CreateCarDealerDto carDealerDto);

    CarDealerResponseDto getCarDealerByCnpj(String cnpj);

    List<CarDealerResponseDto> getAllCarDealers();

    CarDealerResponseDto updateCarDealer(Long id, UpdateCarDealerDto dto);

    DefaultResponse deleteCarDealer(Long id);
}
