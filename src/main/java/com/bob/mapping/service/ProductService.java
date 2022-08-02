package com.bob.mapping.service;

import com.bob.mapping.dto.ProductDto;
import com.bob.mapping.entities.ProductEntity;
import com.bob.mapping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<ProductDto> getProducts(){
        List<ProductDto> productDtos = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();
        for(ProductEntity entity: productEntities){
            ProductDto productDto = new ProductDto();
            productDto.setId(entity.getProductEntityId());
            productDto.setProductName(entity.getProductName());
            productDto.setPrice(entity.getPrice());
            productDtos.add(productDto);

        }
        return productDtos;
    }





}
