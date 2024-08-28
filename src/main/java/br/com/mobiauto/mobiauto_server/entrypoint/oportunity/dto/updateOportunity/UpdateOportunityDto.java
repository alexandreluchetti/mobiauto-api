package br.com.mobiauto.mobiauto_server.entrypoint.oportunity.dto.updateOportunity;

import br.com.mobiauto.mobiauto_server.core.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateOportunityDto(

        @Schema(example = "NOVO, EM_ATENDIMENTO ou CONCLUIDO")
        Status status,

        @Null
        @JsonProperty("motivo_conclusao")
        String reasonConclusion,

        @Null
        @Schema(example = "DD/MM/AAAA")
        @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "A data_conclusao deve estar no formato DD/MM/AAAA.")
        @Size(min = 10, max = 10, message = "A data_conclusao deve estar no formato DD/MM/AAAA.")
        @JsonProperty("data_conclusao")
        String conclusionDate,

        @JsonProperty("cliente_id")
        Long clientId,

        @JsonProperty("veiculo_id")
        Long vehicleId,

        @JsonProperty("usuario_id")
        Long userId,

        @JsonProperty("revenda_id")
        Long carDealerId

) {
}
