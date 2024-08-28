package br.com.mobiauto.mobiauto_server.dataprovider.oportunity.repository;

import br.com.mobiauto.mobiauto_server.configuration.exception.DatabaseException;
import br.com.mobiauto.mobiauto_server.core.entity.oportunity.CreateOportunityRequest;
import br.com.mobiauto.mobiauto_server.core.enums.Status;
import br.com.mobiauto.mobiauto_server.core.shared.Utils;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.OportunityEntity;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.mapper.OportunityEntityRowMapper;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.updateOportunity.UpdateOportunityDto;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class OportunityRepositoryImpl implements OportunityRepository {

    private static final String CALL_GET_OPORTUNITY = "CALL get_oportunity_details(?)";
    private static final String CALL_CREAT_OPORTUNITY = "CALL create_oportunity(?, ?, ?, ?, ?, ?)";
    private static final String CALL_UPDATE_OPORTUNITY = "CALL update_oportunity(?, ?, ?, ?, ?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public OportunityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OportunityEntity> getAllOportunities() {
        try {
            return jdbcTemplate.query(CALL_GET_OPORTUNITY, new OportunityEntityRowMapper(), (Object) null);
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel recuperar as oportunidades do banco de dados");
        }
    }

    @Override
    public OportunityEntity getOportunitiesById(Long id) {
        try {
            return jdbcTemplate.queryForObject(CALL_GET_OPORTUNITY, new OportunityEntityRowMapper(), id);
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel recuperar a oportunidade do banco de dados");
        }
    }

    @Override
    public void createOportunity(CreateOportunityRequest oportunityRequest) {
        try {
            jdbcTemplate.execute(CALL_CREAT_OPORTUNITY, (CallableStatementCallback<Void>) cs -> {
                cs.setString(1, Status.NEW.getValue());
                cs.setDate(2, Date.valueOf(oportunityRequest.getAssignmentDate()));
                cs.setLong(3, oportunityRequest.getClientId());
                cs.setLong(4, oportunityRequest.getVehicleId());
                cs.setLong(5, oportunityRequest.getUserId());
                cs.setLong(6, oportunityRequest.getCarDealerId());
                cs.execute();
                return null;
            });
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel inserir a oportunidade no banco de dados");
        }
    }

    @Override
    public void updateOportunity(Long id, UpdateOportunityDto oportunityDto) {
        try {
            jdbcTemplate.execute(CALL_UPDATE_OPORTUNITY, (CallableStatementCallback<Void>) cs -> {
                cs.setLong(1, id);
                cs.setString(2, oportunityDto.status().getValue());
                setNullableString(cs, 3, oportunityDto.reasonConclusion());
                setNullableDate(cs, 4, oportunityDto.conclusionDate());
                setNullableLong(cs, 5, oportunityDto.clientId());
                setNullableLong(cs, 6, oportunityDto.vehicleId());
                setNullableLong(cs, 7, oportunityDto.userId());
                setNullableLong(cs, 8, oportunityDto.carDealerId());
                cs.execute();
                return null;
            });
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel atualizar a oportunidade no banco de dados");
        }
    }

    private void setNullableString(CallableStatement cs, int index, String string) throws SQLException {
        if (string == null) {
            cs.setNull(index, Types.VARCHAR);
        } else {
            cs.setString(index, string);
        }
    }

    private void setNullableDate(CallableStatement cs, int index, String dateString) throws SQLException {
        if (dateString == null) {
            cs.setNull(index, Types.DATE);
        } else {
            cs.setDate(index, Date.valueOf(Utils.parse(dateString)));
        }
    }

    private void setNullableLong(CallableStatement cs, int index, Long longValue) throws SQLException {
        if (longValue == null) {
            cs.setNull(index, Types.BIGINT);
        } else {
            cs.setLong(index, longValue);
        }
    }
}
