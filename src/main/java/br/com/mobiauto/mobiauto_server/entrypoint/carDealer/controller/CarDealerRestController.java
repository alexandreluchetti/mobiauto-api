package br.com.mobiauto.mobiauto_server.entrypoint.carDealer.controller;

import br.com.mobiauto.mobiauto_server.configuration.exception.InvalidDataException;
import br.com.mobiauto.mobiauto_server.core.entity.DefaultResponse;
import br.com.mobiauto.mobiauto_server.core.shared.CNPJValidator;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerUseCase;
import br.com.mobiauto.mobiauto_server.dataprovider.carDealer.entity.CarDealerResponseDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.CreateCarDealerDto;
import br.com.mobiauto.mobiauto_server.entrypoint.carDealer.dto.UpdateCarDealerDto;
import br.com.mobiauto.mobiauto_server.entrypoint.shared.DefaultResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Revenda")
@RequestMapping("/api/revenda")
public class CarDealerRestController {

    private final CarDealerUseCase useCase;

    public CarDealerRestController(CarDealerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @Operation(summary = "Cria uma revenda")
    public ResponseEntity<DefaultResponseDto> createCarDealer(
            @Valid
            @RequestBody
            CreateCarDealerDto carDealerDto
    ){
        checkCnpj(carDealerDto.cnpj());
        useCase.createCarDealer(carDealerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(DefaultResponseDto.created());
    }

    @GetMapping
    @Operation(summary = "Retorna todas as revendas cadastradas")
    public ResponseEntity<List<CarDealerResponseDto>> getAllCarDealers(){
        return ResponseEntity.ok(useCase.getAllCarDealers());
    }

    @GetMapping("/{cnpj}")
    @Operation(summary = "Retorna uma revenda de acordo com o CNPJ informado")
    public ResponseEntity<CarDealerResponseDto> getCarDealer(
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

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza os dados da revenda de acordo com o id recebido",
            description = """
                    Parametro obrigatorio: id.
                    
                    Os outros atributos, caso nao precisem ser atualizados, inserir **null**
                    """
    )
    public ResponseEntity<CarDealerResponseDto> updateCarDealer(
            @PathVariable
            Long id,

            @Valid
            @RequestBody
            UpdateCarDealerDto dto
    ) {
        return ResponseEntity.ok(useCase.updateCarDealer(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletra a revenda de acordo com o id recebido")
    public ResponseEntity<DefaultResponseDto> deleteCarDealer(@PathVariable Long id){
        DefaultResponse defaultResponse = useCase.deleteCarDealer(id);
        if (defaultResponse.isSuccess()) {
            return ResponseEntity.ok(defaultResponse.toDto());
        } else {
            return ResponseEntity.internalServerError().body(defaultResponse.toDto());
        }
    }

}
