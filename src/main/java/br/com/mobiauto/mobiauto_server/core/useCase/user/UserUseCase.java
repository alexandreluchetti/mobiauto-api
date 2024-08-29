package br.com.mobiauto.mobiauto_server.core.useCase.user;

import br.com.mobiauto.mobiauto_server.core.entity.DefaultResponse;
import br.com.mobiauto.mobiauto_server.core.entity.user.CreateUser;

public interface UserUseCase {

    DefaultResponse createUser(CreateUser createUser);
}
