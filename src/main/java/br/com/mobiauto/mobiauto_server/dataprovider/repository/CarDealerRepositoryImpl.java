package br.com.mobiauto.mobiauto_server.dataprovider.repository;

import br.com.mobiauto.mobiauto_server.configuration.exception.DatabaseException;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.repository.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.dataprovider.repository.mapper.CreateCarDealerDtoRowMapper;
import br.com.mobiauto.mobiauto_server.entrypoint.dto.AddressDto;
import br.com.mobiauto.mobiauto_server.entrypoint.dto.CreateCarDealerDto;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CarDealerRepositoryImpl implements CarDealerRepository {

    private static final String CALL_CREATE_CAR_DEALER = "CALL create_car_dealer(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CALL_GET_CAR_DEALER = "CALL get_car_dealer_by_cnpj(?)";

    private final JdbcTemplate jdbcTemplate;

    public CarDealerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CarDealerResponseDto getCarDealerByCnpj(String cnpj) {
        return jdbcTemplate.queryForObject(CALL_GET_CAR_DEALER, new CreateCarDealerDtoRowMapper(), cnpj);
    }

    @Override
    public List<CarDealerResponseDto> getAllCarDealers() {
        return jdbcTemplate.query(CALL_GET_CAR_DEALER, new CreateCarDealerDtoRowMapper(), (Object) null);
    }

    @Override
    public CarDealerResponseDto createCarDealer(CreateCarDealerDto carDealerDto) {
        try {
            AddressDto address = carDealerDto.address();
            jdbcTemplate.execute(CALL_CREATE_CAR_DEALER, (CallableStatementCallback<Void>) cs -> {
                cs.setString(1, carDealerDto.cnpj());
                cs.setString(2, carDealerDto.companyName());
                cs.setString(3, address.street());
                cs.setString(4, address.number());
                cs.setString(5, address.complement());
                cs.setString(6, address.neighborhood());
                cs.setString(7, address.city());
                cs.setString(8, address.state());
                cs.setString(9, address.zipCode());
                cs.execute();
                return null;
            });
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel inserir a revenda no banco de dados");
        }
        return null;
    }

}
