package br.com.mobiauto.mobiauto_server.dataprovider.user.mapper;

import br.com.mobiauto.mobiauto_server.core.enums.RoleEnum;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.CarDealerEntity;
import br.com.mobiauto.mobiauto_server.dataprovider.user.entity.UserEntity;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.AddressDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEntityRowMapper implements RowMapper<UserEntity> {

    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserEntity(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("email"),
                RoleEnum.from(rs.getString("cargo")),
                new CarDealerEntity(
                        rs.getString("cnpj"),
                        rs.getString("revenda_nome")
                ),
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
