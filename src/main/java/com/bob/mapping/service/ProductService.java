package com.bob.mapping.service;

import com.bob.mapping.dto.ProductDto;
import com.bob.mapping.entities.OrderDetailEntity;
import com.bob.mapping.entities.ProductEntity;
import com.bob.mapping.exception.NoSuchOrderExistsException;
import com.bob.mapping.exception.OrderDetailExistsException;
import com.bob.mapping.repository.OrderDetailRepository;
import com.bob.mapping.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Log4j2
@Service
public class ProductService {
    private ProductRepository productRepository;
    private OrderDetailRepository orderDetailRepository;
    public ProductService(ProductRepository productRepository, OrderDetailRepository orderDetailRepository){
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
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
    public ProductDto getProduct(long productId){
        ProductDto productDto = new ProductDto();
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        if(productEntityOptional.isPresent()){
            log.info("The product with this id " + productId + " found");
            ProductEntity productEntity = productEntityOptional.get();
            productDto.setId(productEntity.getProductEntityId());
            productDto.setProductName(productEntity.getProductName());
            productDto.setPrice(productEntity.getPrice());
        }
        else{
            log.info("The product with this id " + productId + " doesn't exist in database");
            throw new NoSuchOrderExistsException(productId + " doesn't exist in database");
        }
        return productDto;
    }
    public ProductDto deleteProducts(long productId){
        ProductDto productDto = new ProductDto();
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        List<OrderDetailEntity> orderDetailEntityOptional = orderDetailRepository.findByOrderEntityId(productId);
        if(productEntityOptional.isPresent()){
            for(OrderDetailEntity entity: orderDetailEntityOptional){
                orderDetailRepository.delete(entity);
            }
            log.info("The product with this id " + productId + " found");
            ProductEntity productEntity = productEntityOptional.get();
            productRepository.delete(productEntity);
            productDto.setId(productEntity.getProductEntityId());
            productDto.setProductName(productEntity.getProductName());
            productDto.setPrice(productEntity.getPrice());
        }
        else{
            log.info("The product with this id " + productId + "doesn't exist in database");
            throw new NoSuchOrderExistsException(productId + " doesn't exist in database");
        }
        return productDto;
    }
    public ProductDto deleteProducts1(long productId){
        ProductDto productDto = new ProductDto();
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        List<OrderDetailEntity> orderDetailEntityOptional = orderDetailRepository.findByOrderEntityId(productId);
        if(productEntityOptional.isPresent()){
            if(orderDetailEntityOptional.size() == 0){
                log.info("The product with this id " + productId + " found");
                ProductEntity productEntity = productEntityOptional.get();
                productRepository.delete(productEntity);
                productDto.setId(productEntity.getProductEntityId());
                productDto.setProductName(productEntity.getProductName());
                productDto.setPrice(productEntity.getPrice());
            }
            else{
                log.info("Product Id " + productId + " has order details associated with it");
                throw new OrderDetailExistsException(productId + " has order details associated with it");
            }
        }
        else{
            log.info("The product with this id " + productId + " doesn't exist in database");
            throw new NoSuchOrderExistsException(productId + " doesn't exist in database");
        }
        return productDto;
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
