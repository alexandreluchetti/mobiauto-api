package br.com.mobiauto.mobiauto_server.entrypoint.shared;

public record DefaultResponseDto(

        Integer code,

        String message

) {

    public static DefaultResponseDto created() {
        return new DefaultResponseDto(201, "Sucesso");
    }
}
