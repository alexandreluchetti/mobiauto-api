package br.com.mobiauto.mobiauto_server.dataprovider.user.repository;

import br.com.mobiauto.mobiauto_server.configuration.exception.DatabaseException;
import br.com.mobiauto.mobiauto_server.dataprovider.user.entity.UserEntity;
import br.com.mobiauto.mobiauto_server.dataprovider.user.entity.UserValidationEntity;
import br.com.mobiauto.mobiauto_server.dataprovider.user.mapper.UserEntityRowMapper;
import br.com.mobiauto.mobiauto_server.dataprovider.user.mapper.UserValidationEntityRowMapper;
import br.com.mobiauto.mobiauto_server.core.entity.user.CreateUser;
import br.com.mobiauto.mobiauto_server.core.useCase.user.UserRepository;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String CALL_ISERT_USER = "CALL insert_user(?, ?, ?, ?, ?, ?);";
    private static final String CALL_GET_USER_BY_ID = "CALL get_user_by_id(?);";
    private static final String CALL_GET_USER_BY_EMAIL = "select * from usuario where email = ?;";

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserEntity getUserById(Long id) {
        try {
            return jdbcTemplate.queryForObject(CALL_GET_USER_BY_ID, new UserEntityRowMapper(), id);
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel encontrar o usuario com o id: " + id);
        }
    }

    @Override
    public List<UserEntity> getUsers() {
        try {
            return jdbcTemplate.query(CALL_GET_USER_BY_ID, new UserEntityRowMapper(), null);
        } catch (Exception e) {
            throw new DatabaseException("Nenhum usuario retornado");
        }
    }

    @Override
    public UserValidationEntity getUserByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(CALL_GET_USER_BY_EMAIL, new UserValidationEntityRowMapper(), email);
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel encontrar o usuario com o email: " + email);
        }
    }

    @Override
    public void createUser(CreateUser createUser) {
        try {
            jdbcTemplate.execute(CALL_ISERT_USER, (CallableStatementCallback<Void>) cs -> {
                cs.setString(1, createUser.getName());
                cs.setString(2, createUser.getEmail());
                cs.setString(3, createUser.getPassword());
                cs.setString(4, createUser.getRoleEnum().getValue());
                cs.setLong(5, createUser.getCarDealerId());
                cs.setLong(6, createUser.getAddressId());
                cs.executeUpdate();
                return null;
            });
        } catch (Exception e) {
            throw new DatabaseException("Nao foi possivel salvar o usuario");
        }
    }
}
