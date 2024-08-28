package br.com.mobiauto.mobiauto_server.dataprovider.oportunity.mapper;

import br.com.mobiauto.mobiauto_server.core.enums.Position;
import br.com.mobiauto.mobiauto_server.core.enums.Status;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OportunityEntityRowMapper implements RowMapper<OportunityEntity> {

    @Override
    public OportunityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OportunityEntity(
                rs.getLong("oportunidade_id"),
                Status.from(rs.getString("status")),
                rs.getString("motivo_conclusao"),
                getLocalDate(rs, "data_atribuicao"),
                getLocalDate(rs, "data_conclusao"),
                rs.getBoolean("ativo"),
                new ClientEntity(
                        rs.getString("cliente_nome"),
                        rs.getString("cliente_email"),
                        rs.getString("cliente_telefone")
                ),
                new VehicleEntity(
                        rs.getString("veiculo_marca"),
                        rs.getString("veiculo_modelo"),
                        rs.getString("veiculo_versao"),
                        rs.getInt("veiculo_ano_modelo")
                ),
                new UserEntity(
                        rs.getString("usuario_nome"),
                        rs.getString("usuario_email"),
                        Position.from(rs.getString("usuario_cargo"))
                ),
                new CarDealerEntity(
                        rs.getString("revenda_cnpj"),
                        rs.getString("revenda_nome_social")
                )
        );
    }

    private LocalDate getLocalDate(ResultSet rs, String column) throws SQLException {
        Date date = rs.getDate(column);
        if (date == null) {
            return null;
        } else {
            return date.toLocalDate();
        }
    }
}
