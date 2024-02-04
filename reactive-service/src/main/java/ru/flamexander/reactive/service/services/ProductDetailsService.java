package ru.flamexander.reactive.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.flamexander.reactive.service.dtos.BuilderProductDetailsDto;
import ru.flamexander.reactive.service.dtos.ProductDetailsDto;
import ru.flamexander.reactive.service.entities.Product;
import ru.flamexander.reactive.service.integrations.ProductDetailsServiceIntegration;
import ru.flamexander.reactive.service.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {
    private final ProductDetailsServiceIntegration productDetailsServiceIntegration;
    private final ProductRepository productRepository;

    public Mono<ProductDetailsDto> getProductDetailsById(Long id) {
        return Mono.just(1L)
                .map(BuilderProductDetailsDto::new)
                .zipWith(productRepository.findById(id))
                .map(e -> e.getT1().withName(e.getT2().getName()))
                .zipWith(productDetailsServiceIntegration.getProductDetailsById(id))
                .map(e -> e.getT1().withDescription(e.getT2().getDescription()))
                .map(BuilderProductDetailsDto::build)
                .switchIfEmpty(Mono.error(IllegalArgumentException::new));
    }

    public Flux<ProductDetailsDto> getProductDetailsByIds(List<Long> ids) {
        return Flux.concat(ids.stream().map(this::getProductDetailsById).toList());
    }

    public Flux<ProductDetailsDto> getAllProducts() {
        return Flux.concat(this.getProductDetailsById(1L), this.getProductDetailsById(3L));
    }

}
