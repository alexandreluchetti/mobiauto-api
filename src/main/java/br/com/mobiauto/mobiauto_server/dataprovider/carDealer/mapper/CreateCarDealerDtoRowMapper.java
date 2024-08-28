package br.com.mobiauto.mobiauto_server.dataprovider.carDealer.mapper;

import br.com.mobiauto.mobiauto_server.dataprovider.carDealer.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.AddressDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateCarDealerDtoRowMapper implements RowMapper<CarDealerResponseDto> {

    @Override
    public CarDealerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CarDealerResponseDto(
                rs.getLong("revenda_id"),
                rs.getString("cnpj"),
                rs.getString("nome_social"),
                new AddressDto(
                    rs.getString("rua"),
                    rs.getString("numero"),
                    rs.getString("complemento"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("cep")
                )
        );
    }
}
