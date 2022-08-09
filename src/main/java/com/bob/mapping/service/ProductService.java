package com.bob.mapping.service;

import com.bob.mapping.dto.ProductDto;
import com.bob.mapping.entities.ProductEntity;
import com.bob.mapping.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<ProductDto> getProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();
        for (ProductEntity entity : productEntities) {
            ProductDto productDto = new ProductDto();
            productDto.setId(entity.getProductEntityId());
            productDto.setProductName(entity.getProductName());
            productDto.setPrice(entity.getPrice());
            productDtos.add(productDto);

        }
        return productDtos;
    }
    public ProductDto createProduct(@RequestBody ProductDto body){
        ProductEntity entity = new ProductEntity();
        entity.setProductEntityId(body.getId());
        entity.setProductName(body.getProductName());
        entity.setPrice(body.getPrice());
        productRepository.save(entity);

        ProductDto productDto = new ProductDto();
        productDto.setId(entity.getProductEntityId());
        productDto.setProductName(entity.getProductName());
        productDto.setPrice(entity.getPrice());
        return productDto;
    }




}
