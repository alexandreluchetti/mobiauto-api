package br.com.mobiauto.mobiauto_server.configuration.CarDealer;

import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerUseCase;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.impl.CarDealerUseCaseImpl;
import br.com.mobiauto.mobiauto_server.dataprovider.repository.CarDealerRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class CarDealerConfiguration {

    @Bean
    public CarDealerUseCase loadCarDealerUseCase(CarDealerRepository carDealerRepository) {
        return new CarDealerUseCaseImpl(carDealerRepository);
    }

    @Bean
    public CarDealerRepository loadCarDealerRepository(JdbcTemplate jdbcTemplate) {
        return new CarDealerRepositoryImpl(jdbcTemplate);
    }
}
