package com.bob.mapping.service;


import com.bob.mapping.dto.Item;
import com.bob.mapping.dto.Receipt;
import com.bob.mapping.entities.ItemData;
import com.bob.mapping.entities.OrderDetailEntity;
import com.bob.mapping.entities.OrderEntity;
import com.bob.mapping.entities.ProductEntity;
import com.bob.mapping.repository.OrderDetailRepository;
import com.bob.mapping.repository.OrderRepository;
import com.bob.mapping.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ReceiptService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    //By annotating OrderRepository with @Repository, you tell Spring to instantiate (create) an object of OrderRepository
    //you just get the reference and use it.
    //you pass the reference via the constructor
    //by annotating this class with @Service, you tell Spring to create an instance of ReceiptService and pass a reference to the object
    //of OrderRepository created by Spring.
    //This is called dependency injection.
    //Basically you don't have to use new to instantiate an object. Instead, Spring will do that for you. You can just use
    //it by getting the reference.

    //after you get the reference of the object, you can use the method to communicate with it.
    public ReceiptService(OrderRepository orderRepository,
                          OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;

    }

//    public ReceiptService(OrderDetailRepository orderDetailRepository){
//        this.orderDetailRepository = orderDetailRepository;
//    }

    public Receipt calcReceipt(long orderId) {
        //get OrderEntity
        //OptionalFramework

        //I am going to create a Receipt object and return back which contain the information.
        // 1.instantiate the object.
        //2.update the attribute of the object
        //2.1 go to database to find the order.
        //2.2 update the attribute of the object of Receipt with the info I get from the database.
        //3. return back
        Receipt receipt = new Receipt();
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);

        if (orderEntityOptional.isPresent()) {
            //do something with it
            log.info("The order with this id " + orderId + "  found");

            OrderEntity orderEntity = orderEntityOptional.get();
            receipt.setOrderId(orderEntity.getOrderEntityId());
            receipt.setCustomerName(orderEntity.getCustomerName());
            receipt.setTotalPrice(10);

        } else {

            log.info("The order with this id " + orderId + " doesn't exist in database");
        }

        return receipt;
    }

    //recursive calcReceipt1
    public Receipt calcReceipt1(long orderId) {
        Receipt receipt = calcReceipt(orderId);

        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByOrderEntityId(orderId);
        double totalPrice = 0;

        List<Item> listofItems = new ArrayList<>();

        for (OrderDetailEntity orderDetailEntity : orderDetailEntities) {
            double quantityForThisItem = orderDetailEntity.getQuantity();
            ProductEntity productEntity = orderDetailEntity.getProductEntity();
//            Long productId = orderDetailEntity.getProductEntityId();
            double price = productEntity.getPrice();
            totalPrice = totalPrice+ quantityForThisItem + price;
            Item item = new Item();
            item.setProductId(productEntity.getProductEntityId());
            item.setProductName(productEntity.getProductName());
            item.setProductPrice(productEntity.getPrice());
            item.setQuantity(orderDetailEntity.getQuantity());
            listofItems.add(item);
        }
;
        receipt.setTotalPrice(totalPrice);
        receipt.setItems(listofItems);
        return receipt;

    }
    public Receipt calcReceipt2(long orderId) {
        Receipt receipt = calcReceipt(orderId);

        List<ItemData> itemDatas  = orderDetailRepository.getItems(orderId);
        double totalPrice = 0;

        List<Item> listofItems = new ArrayList<>();

        for(ItemData itemData: itemDatas) {
            double price = itemData.getPrice();
            String name = itemData.getProductName();
            int quantity = itemData.getQuantity();
            Long productId = itemData.getProductEntityId();
            totalPrice = totalPrice + quantity * price;
            Item item = new Item();
            item.setProductId((long)productId);
            item.setProductName(name);
            item.setProductPrice(price);
            item.setQuantity(quantity);
            listofItems.add(item);

        }

//        for (OrderDetailEntity orderDetailEntity : orderDetailEntities) {
//            double quantityForThisItem = orderDetailEntity.getQuantity();
//            ProductEntity productEntity = orderDetailEntity.getProductEntity();
////            Long productId = orderDetailEntity.getProductEntityId();
//            double price = productEntity.getPrice();
//            totalPrice = totalPrice+ quantityForThisItem + price;
//            Item item = new Item();
//            item.setProductId(productEntity.getProductEntityId());
//            item.setProductName(productEntity.getProductName());
//            item.setProductPrice(productEntity.getPrice());
//            item.setQuantity(orderDetailEntity.getQuantity());
//            listofItems.add(item);
//        }
        ;
        receipt.setTotalPrice(totalPrice);
        receipt.setItems(listofItems);
        return receipt;

    }

}
//log

