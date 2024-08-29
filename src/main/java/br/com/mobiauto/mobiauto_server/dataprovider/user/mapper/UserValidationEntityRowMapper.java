package br.com.mobiauto.mobiauto_server.dataprovider.user.mapper;

import br.com.mobiauto.mobiauto_server.core.enums.RoleEnum;
import br.com.mobiauto.mobiauto_server.dataprovider.user.entity.UserValidationEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserValidationEntityRowMapper implements RowMapper<UserValidationEntity> {
    @Override
    public UserValidationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserValidationEntity(
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("senha"),
                RoleEnum.from(rs.getString("cargo"))
        );
    }
}
