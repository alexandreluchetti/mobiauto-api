package br.com.mobiauto.mobiauto_server.entrypoint.oportunity.controller;

import br.com.mobiauto.mobiauto_server.core.useCase.oportunity.OportunityUseCase;
import br.com.mobiauto.mobiauto_server.dataprovider.oportunity.entity.OportunityEntity;
import br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.CreateOportunityDto;
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
    public ResponseEntity<List<OportunityEntity>> getAllOprtunities() {
        return ResponseEntity.ok(useCase.getAllOportunities());
    }

}
