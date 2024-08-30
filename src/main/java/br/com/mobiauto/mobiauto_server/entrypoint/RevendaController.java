package br.com.mobiauto.mobiauto_server.entrypoint;

import br.com.mobiauto.mobiauto_server.core.entity.Revenda;
import br.com.mobiauto.mobiauto_server.core.shared.CNPJ;
import br.com.mobiauto.mobiauto_server.core.useCase.RevendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Revendas")
@RequestMapping("/revendas")
public class RevendaController {

    @Autowired
    private RevendaService revendaService;

    @GetMapping
    public ResponseEntity<List<Revenda>> getAllRevendas() {
        return new ResponseEntity<>(revendaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Revenda> getRevendaById(@PathVariable Long id) {
        Optional<Revenda> revenda = revendaService.findById(id);
        return revenda.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Revenda> createRevenda(@RequestBody Revenda revenda) {
        CNPJ.validation(revenda.getCnpj());
        return new ResponseEntity<>(revendaService.save(revenda), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Revenda> updateRevenda(@PathVariable Long id, @RequestBody Revenda revenda) {
        if (!revendaService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        revenda.setId(id);
        return new ResponseEntity<>(revendaService.save(revenda), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevenda(@PathVariable Long id) {
        if (!revendaService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        revendaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
