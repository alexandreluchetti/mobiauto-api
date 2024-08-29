package br.com.mobiauto.mobiauto_server.configuration.user;

import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.user.UserRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.user.UserUseCase;
import br.com.mobiauto.mobiauto_server.core.useCase.user.impl.UserUseCaseImpl;
import br.com.mobiauto.mobiauto_server.dataprovider.user.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {

    @Bean
    public UserUseCase loadUserUseCase(
            UserRepository userRepository,
            CarDealerRepository carDealerRepository,
            PasswordEncoder passwordEncoder
    ) {
        return new UserUseCaseImpl(userRepository, carDealerRepository, passwordEncoder);
    }

    @Bean
    public UserRepository loadUserRepository(JdbcTemplate jdbcTemplate) {
        return new UserRepositoryImpl(jdbcTemplate);
    }
}
