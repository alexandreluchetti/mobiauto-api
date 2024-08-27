package br.com.mobiauto.mobiauto_server.dataprovider.repository.entity;

public record VehicleDto(

        Long id,
        String brand,
        String model,
        String version,
        Integer year

) {
}
