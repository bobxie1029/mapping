package com.bob.mapping.controller;

import com.bob.mapping.dto.ProductDto;
import com.bob.mapping.dto.Receipt;
import com.bob.mapping.service.ProductService;
import com.bob.mapping.service.ReceiptService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
public class ProductController {
    private ProductService productService;
    private ReceiptService receiptService;
    public ProductController(ProductService productService, ReceiptService receiptService){
        this.productService = productService;
        this.receiptService = receiptService;
    }
    @GetMapping("/listProducts")
    public List<ProductDto> listAllProduct(){
        return productService.getProducts();
    }
    @GetMapping("product1/{id}")
    public Receipt product1(@PathVariable("id") int id){
        return receiptService.calcReceipt1(id);
    }

    @GetMapping("product2/{id}")
    public Receipt product2(@PathVariable("id") int id){
        return receiptService.calcReceipt2(id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ProductDto deleteProduct(@PathVariable("id") int id){
        return productService.deleteProducts(id);
    }
    @PostMapping("/product")
    public ProductDto postProduct(@RequestBody ProductDto body){
        log.info("Received product name " + body.getProductName() + " with price " + body.getPrice());
        ProductDto productDto = new ProductDto();
        log.info("assigned id "  + body.getId());
        return productDto;
    }

}
