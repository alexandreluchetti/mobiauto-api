package br.com.mobiauto.mobiauto_server.entrypoint;

import br.com.mobiauto.mobiauto_server.core.entity.Oportunidade;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunidade.OportunidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Oportunidades")
@RequestMapping("/oportunidades")
public class OportunidadeController {

    @Autowired
    private OportunidadeService oportunidadeService;

    @GetMapping
    public ResponseEntity<List<Oportunidade>> getAllOportunidades() {
        return new ResponseEntity<>(oportunidadeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> getOportunidadeById(@PathVariable Long id) {
        Optional<Oportunidade> oportunidade = oportunidadeService.findById(id);
        return oportunidade.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Oportunidade> createOportunidade(@RequestBody Oportunidade oportunidade) {
        return new ResponseEntity<>(oportunidadeService.save(oportunidade), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oportunidade> updateOportunidade(@PathVariable Long id, @RequestBody Oportunidade oportunidade) {
        Oportunidade updatedOportunidade = oportunidadeService.update(id, oportunidade);
        return new ResponseEntity<>(updatedOportunidade, HttpStatus.OK);
    }

    @PutMapping("/{id}/transferir/{novoAssistenteId}")
    public ResponseEntity<Void> transferirOportunidade(@PathVariable Long id, @PathVariable Long novoAssistenteId) {
        oportunidadeService.transferirOportunidade(id, novoAssistenteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOportunidade(@PathVariable Long id) {
        if (!oportunidadeService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oportunidadeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
