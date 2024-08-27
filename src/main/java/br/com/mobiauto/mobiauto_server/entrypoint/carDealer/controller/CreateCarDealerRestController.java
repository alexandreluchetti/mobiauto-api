package br.com.mobiauto.mobiauto_server.entrypoint.carDealer.controller;

import br.com.mobiauto.mobiauto_server.configuration.exception.InvalidDataException;
import br.com.mobiauto.mobiauto_server.core.entity.CarDealer;
import br.com.mobiauto.mobiauto_server.core.shared.CNPJValidator;
import br.com.mobiauto.mobiauto_server.core.useCase.carDealer.CarDealerUseCase;
import br.com.mobiauto.mobiauto_server.entrypoint.dto.CreateCarDealerDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Mobiauto API")
@RequestMapping("/api/revenda")
public class CreateCarDealerRestController {

    private final CarDealerUseCase useCase;

    public CreateCarDealerRestController(CarDealerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<CarDealer> createCarDealer(
            @Valid
            @RequestBody
            CreateCarDealerDto carDealerDto
    ){
        if (CNPJValidator.isValidCNPJ(carDealerDto.cnpj())) {
            throw new InvalidDataException("CNPJ Invalido");
        }

        useCase.createCarDealer(carDealerDto);
        return ResponseEntity.noContent().build();
    }
}
