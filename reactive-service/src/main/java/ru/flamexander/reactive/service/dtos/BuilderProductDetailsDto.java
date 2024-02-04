package ru.flamexander.reactive.service.dtos;

public class BuilderProductDetailsDto {
    private final Long id;
    private String name;
    private String description;

    public BuilderProductDetailsDto(Long id) {
        this.id = id;
    }

    public BuilderProductDetailsDto withName(String name) {
        this.name = name;
        return this;
    }

    public BuilderProductDetailsDto withDescription(String description) {
        this.description = description;
        return this;
    }


    public ProductDetailsDto build() {
        return new ProductDetailsDto(id, name, description);
    }
}
