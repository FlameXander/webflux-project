package ru.flamexander.reactive.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.flamexander.reactive.service.dtos.ProductDetailsDto;
import ru.flamexander.reactive.service.services.ProductDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detailed")
@RequiredArgsConstructor
public class ProductsDetailsController {
    private final ProductDetailsService productDetailsService;

    @GetMapping("/{id}")
    public Mono<ProductDetailsDto> getManySlowProducts(@PathVariable Long id) {
        return productDetailsService.getProductDetailsById(id);
    }

    @GetMapping("/many/{ids}")
    public Flux<ProductDetailsDto> getByListSlowProducts(@PathVariable List<Long> ids) {
        return productDetailsService.getProductDetailsByIds(ids);
    }

    @GetMapping("/demo")
    public Flux<ProductDetailsDto> getAllSlowProducts() {
        return productDetailsService.getAllProducts();
    }
}
