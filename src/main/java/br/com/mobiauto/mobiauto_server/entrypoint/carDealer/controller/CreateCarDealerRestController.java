package br.com.mobiauto.mobiauto_server.entrypoint.carDealer.controller;

import br.com.mobiauto.mobiauto_server.configuration.exception.InvalidDataException;
import br.com.mobiauto.mobiauto_server.core.shared.CNPJValidator;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerUseCase;
import br.com.mobiauto.mobiauto_server.dataprovider.repository.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.dto.CreateCarDealerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Mobiauto API")
@RequestMapping("/api/revenda")
public class CreateCarDealerRestController {

    private final CarDealerUseCase useCase;

    public CreateCarDealerRestController(CarDealerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @Operation(summary = "Cria uma revenda")
    public ResponseEntity<CarDealerResponseDto> createCarDealer(
            @Valid
            @RequestBody
            CreateCarDealerDto carDealerDto
    ){
        checkCnpj(carDealerDto.cnpj());
        return ResponseEntity.ok(useCase.createCarDealer(carDealerDto));
    }

    @GetMapping
    @Operation(summary = "Retorna uma revenda de acordo com o CNPJ informado")
    public ResponseEntity<List<CarDealerResponseDto>> createCarDealer(){
        return ResponseEntity.ok(useCase.getAllCarDealers());
    }

    @GetMapping("/{cnpj}")
    @Operation(summary = "Retorna uma revenda de acordo com o CNPJ informado")
    public ResponseEntity<CarDealerResponseDto> createCarDealer(
            @Size(min = 14, max = 14)
            @Parameter(description = "O CNPJ deve ter 14 caracteres (apenas numeros).")
            @PathVariable
            String cnpj
    ){
        checkCnpj(cnpj);
        return ResponseEntity.ok(useCase.getCarDealerByCnpj(cnpj));
    }

    private void checkCnpj(String cnpj){
        if (!CNPJValidator.isValidCNPJ(cnpj)) {
            throw new InvalidDataException("CNPJ Invalido");
        }
    }
}
