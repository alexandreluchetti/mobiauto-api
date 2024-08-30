package br.com.mobiauto.mobiauto_server.core.useCase;

import br.com.mobiauto.mobiauto_server.core.entity.Oportunidade;
import br.com.mobiauto.mobiauto_server.dataprovider.repositorios.OportunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OportunidadeService {

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    public List<Oportunidade> findAll() {
        return oportunidadeRepository.findAll();
    }

    public Optional<Oportunidade> findById(Long id) {
        return oportunidadeRepository.findById(id);
    }

    public Oportunidade save(Oportunidade oportunidade) {
        return oportunidadeRepository.save(oportunidade);
    }

    public void deleteById(Long id) {
        oportunidadeRepository.deleteById(id);
    }
}
