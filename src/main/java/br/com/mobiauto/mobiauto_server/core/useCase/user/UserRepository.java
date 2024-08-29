package br.com.mobiauto.mobiauto_server.core.useCase.user;

import br.com.mobiauto.mobiauto_server.dataprovider.user.entity.UserEntity;
import br.com.mobiauto.mobiauto_server.dataprovider.user.entity.UserValidationEntity;
import br.com.mobiauto.mobiauto_server.core.entity.user.CreateUser;

import java.util.List;

public interface UserRepository {

    UserEntity getUserById(Long id);

    List<UserEntity> getUsers();

    UserValidationEntity getUserByEmail(String email);

    void createUser(CreateUser createUser);
}
