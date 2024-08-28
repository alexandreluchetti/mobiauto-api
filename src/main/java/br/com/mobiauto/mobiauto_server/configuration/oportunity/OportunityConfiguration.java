package br.com.mobiauto.mobiauto_server.configuration.oportunity;

import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityRepository;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityUseCase;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.impl.OportunityUseCaseImpl;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.repository.OportunityRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class OportunityConfiguration {

    @Bean
    public OportunityUseCase loadOportunityUseCase(OportunityRepository repository) {
        return new OportunityUseCaseImpl(repository);
    }

    @Bean
    public OportunityRepository loadOportunityRepository(JdbcTemplate jdbcTemplate) {
        return new OportunityRepositoryImpl(jdbcTemplate);
    }

}
