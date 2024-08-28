package br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto;

import br.com.mobiauto.mobiauto_server.core.entity.oportunity.CreateOportunityRequest;
import br.com.mobiauto.mobiauto_server.core.enums.Status;
import br.com.mobiauto.mobiauto_server.core.shared.Utils;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateOportunityDto(

        @NotBlank
        @Schema(example = "DD/MM/AAAA")
        @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "A data_atribuicao deve estar no formato DD/MM/AAAA.")
        @Size(min = 10, max = 10, message = "A data_atribuicao deve estar no formato DD/MM/AAAA.")
        @JsonProperty("data_atribuicao")
        String assignmentDate,

        @JsonProperty("cliente_id")
        Long clientId,

        @JsonProperty("veiculo_id")
        Long vehicleId,

        @JsonProperty("usuario_id")
        Long userId,

        @JsonProperty("revenda_id")
        Long carDealerId

) {

        public CreateOportunityRequest toRequest() {
                return new CreateOportunityRequest(
                        Status.NEW,
                        Utils.parse(this.assignmentDate),
                        this.clientId,
                        this.vehicleId,
                        this.userId,
                        this.carDealerId
                );
        }
}
