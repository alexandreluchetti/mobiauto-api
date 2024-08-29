package br.com.mobiauto.mobiauto_server.core.useCase.user.impl;

import br.com.mobiauto.mobiauto_server.configuration.exception.NoneResultException;
import br.com.mobiauto.mobiauto_server.core.entity.DefaultResponse;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.user.UserRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.user.UserUseCase;
import br.com.mobiauto.mobiauto_server.dataprovider.carDealer.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.core.entity.user.CreateUser;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository userRepository;
    private final CarDealerRepository carDealerRepository;
    private final PasswordEncoder passwordEncoder;

    public UserUseCaseImpl(
            UserRepository userRepository,
            CarDealerRepository carDealerRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.carDealerRepository = carDealerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public DefaultResponse createUser(CreateUser createUser) {
        CarDealerResponseDto carDealer = carDealerRepository.getCarDealerByIdOrCnpj(createUser.getCarDealerId(), null);
        if (carDealer == null) throw new NoneResultException("Nao existe uma revenda com esse id");

        createUser.setPassword(this.passwordEncoder.encode(createUser.getPassword()));
        userRepository.createUser(createUser);
        return DefaultResponse.created();
    }
}
