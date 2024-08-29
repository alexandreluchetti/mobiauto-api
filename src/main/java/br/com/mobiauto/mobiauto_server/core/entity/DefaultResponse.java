package br.com.mobiauto.mobiauto_server.core.entity;

import br.com.mobiauto.mobiauto_server.entrypoint.shared.DefaultResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DefaultResponse {

    private Integer code;
    private String message;

    public static DefaultResponse created() {
        return new DefaultResponse(201, "Created");
    }

    public DefaultResponseDto toDto() {
        return new DefaultResponseDto(code, message);
    }

    public static DefaultResponse success() {
        return new DefaultResponse(200, "Success");
    }

    public boolean isSuccess() {
        return this.code == 200;
    }
}
