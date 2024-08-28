package br.com.mobiauto.mobiauto_server.core.useCase.carDealer;

import br.com.mobiauto.mobiauto_server.dataprovider.carDealer.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.CreateCarDealerDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.UpdateCarDealerDto;

import java.util.List;

public interface CarDealerRepository {

    CarDealerResponseDto getCarDealerByIdOrCnpj(Long id, String cnpj);

    List<CarDealerResponseDto> getAllCarDealers();

    void createCarDealer(CreateCarDealerDto carDealerDto);

    CarDealerResponseDto updateCarDealer(Long id, UpdateCarDealerDto dto);

    void deleteCarDealer(Long id);
}
