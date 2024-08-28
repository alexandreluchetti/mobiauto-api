package br.com.mobiauto.mobiauto_server.dataprovider.carDealer.repository;

import br.com.mobiauto.mobiauto_server.configuration.exception.DatabaseException;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.carDealer.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.dataprovider.carDealer.mapper.CreateCarDealerDtoRowMapper;
import br.com.mobiauto.mobiauto_server.dataprovider.shared.AbsRepository;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.AddressDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.CreateCarDealerDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.UpdateCarDealerDto;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CarDealerRepositoryImpl extends AbsRepository implements CarDealerRepository {

    private static final String CALL_CREATE_CAR_DEALER = "CALL create_car_dealer(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CALL_GET_CAR_DEALER_BY_CNPJ = "CALL get_car_dealer_by_cnpj(?)";
    private static final String CALL_GET_CAR_DEALER_BY_ID_OR_CNPJ = "CALL get_car_dealer_by_id_or_cnpj(?, ?)";
    private static final String CALL_UPDATE_CAR_DEALER = "CALL update_car_dealer(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CALL_DELETE_CAR_DEALER = "CALL deactivate_car_dealer_by_id(?)";

    private final JdbcTemplate jdbcTemplate;

    public CarDealerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CarDealerResponseDto getCarDealerByIdOrCnpj(Long id, String cnpj) {
        try {
            return jdbcTemplate.queryForObject(CALL_GET_CAR_DEALER_BY_ID_OR_CNPJ, new CreateCarDealerDtoRowMapper(), id, cnpj);
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel buscar a revenda no banco de dados");
        }
    }

    @Override
    public List<CarDealerResponseDto> getAllCarDealers() {
        try {
            return jdbcTemplate.query(CALL_GET_CAR_DEALER_BY_ID_OR_CNPJ, new CreateCarDealerDtoRowMapper(), null, null);
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel buscar as revendas no banco de dados");
        }
    }

    @Override
    public void createCarDealer(CreateCarDealerDto carDealerDto) {
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
    }

    @Override
    public CarDealerResponseDto updateCarDealer(Long id, UpdateCarDealerDto dto) {
        try {
            AddressDto address = dto.addressDto();
            jdbcTemplate.execute(CALL_UPDATE_CAR_DEALER, (CallableStatementCallback<Void>) cs -> {
                cs.setLong(1, id);
                setNullableString(cs,2, dto.companyName());
                setNullableString(cs,3, address.street());
                setNullableString(cs,4, address.number());
                setNullableString(cs,5, address.complement());
                setNullableString(cs,6, address.neighborhood());
                setNullableString(cs,7, address.city());
                setNullableString(cs,8, address.state());
                setNullableString(cs,9, address.zipCode());
                cs.execute();
                return null;
            });
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel atualizar os dados a revenda no banco de dados");
        }
        return getCarDealerByIdOrCnpj(id, null);
    }

    @Override
    public void deleteCarDealer(Long id) {
        try {
            jdbcTemplate.execute(CALL_DELETE_CAR_DEALER, (CallableStatementCallback<Void>) cs -> {
                cs.setLong(1, id);
                cs.execute();
                return null;
            });
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel deletar a revenda no banco de dados");
        }
    }

}
