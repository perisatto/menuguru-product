package com.perisatto.fiapprj.menuguru_product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.perisatto.fiapprj.menuguru_product.application.interfaces.ProductRepository;
import com.perisatto.fiapprj.menuguru_product.application.usecases.ProductUseCase;
import com.perisatto.fiapprj.menuguru_product.infra.gateways.ProductRepositoryJpa;
import com.perisatto.fiapprj.menuguru_product.infra.gateways.mappers.ProductMapper;
import com.perisatto.fiapprj.menuguru_product.infra.persistences.repositories.ProductPersistenceRepository;

@Configuration
public class ProductConfig {

	@Bean
	ProductUseCase productUseCase(ProductRepository productRepository) {
		return new ProductUseCase(productRepository);
	}	
	
	@Bean
	ProductRepositoryJpa productJpaRepository(ProductPersistenceRepository productPersistenceRepository, ProductMapper productMapper) {
		return new ProductRepositoryJpa(productPersistenceRepository, productMapper);
	}
	
	@Bean
	ProductMapper productMapper() {
		return new ProductMapper();
	}
}
