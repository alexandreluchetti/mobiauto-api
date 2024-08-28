package br.com.mobiauto.mobiauto_server.entrypoint.oportunity.controller;

import br.com.mobiauto.mobiauto_server.core.entity.DefaultResponse;
import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityUseCase;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.OportunityEntity;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.createOportunity.CreateOportunityDto;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.getOportunities.OportunityResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.updateOportunity.UpdateOportunityDto;
import br.com.mobiauto.mobiauto_server.entrypoint.shared.DefaultResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Oportunidade")
@RequestMapping("/api/oportunidade")
public class OportunityRestController {

    private final OportunityUseCase useCase;

    public OportunityRestController(OportunityUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @Operation(summary = "Cria uma nova oportunidade")
    public ResponseEntity<DefaultResponseDto> createOportunity(
            @Valid
            @RequestBody
            CreateOportunityDto createOportunityDto
    ){
        useCase.createOportunity(createOportunityDto.toRequest());
        return ResponseEntity.status(HttpStatus.CREATED).body(DefaultResponseDto.created());
    }

    @GetMapping
    @Operation(summary = "Retorna todas as oportunidades cadastradas")
    public ResponseEntity<List<OportunityResponseDto>> getAllOprtunities() {
        return ResponseEntity.ok(useCase.getAllOportunities().stream().map(OportunityEntity::toDto).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna a oportunidade de acordo com o id recebido")
    public ResponseEntity<OportunityResponseDto> getOportunityById(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.getOportunityById(id).toDto());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza os dados da oportunidade de acordo com o id recebido",
            description = """
                    Parametros obrigatorios: id e status.
                    
                    Os outros atributos, caso nao precisem ser atualizados, inserir **null**
                    """
    )
    public ResponseEntity<OportunityResponseDto> updateOportunity(
            @PathVariable
            Long id,

            @Valid
            @RequestBody
            UpdateOportunityDto updateOportunityDto
    ) {
        return ResponseEntity.ok(useCase.updateOportunity(id, updateOportunityDto).toDto());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta a oportunidade de acordo com o id recebido")
    public ResponseEntity<DefaultResponseDto> deleteOportunity(
            @PathVariable
            Long id
    ) {
        DefaultResponse defaultResponse = useCase.deleteOportunity(id);
        if (defaultResponse.isSuccess()) {
            return ResponseEntity.ok(defaultResponse.toDto());
        } else {
            return ResponseEntity.internalServerError().body(defaultResponse.toDto());
        }
    }

    @GetMapping("/revenda/{id}")
    @Operation(summary = "Retorna todas as oportunidades que a revenda possui")
    public ResponseEntity<List<OportunityResponseDto>> getOportunitiesByCarDealer(@PathVariable Long id){
        return ResponseEntity.ok(useCase.getOportunitiesByCarDealer(id).stream().map(OportunityEntity::toDto).toList());
    }

}
