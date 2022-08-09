package com.bob.mapping.helper;

import com.bob.mapping.service.OrderService;
import org.junit.jupiter.api.function.Executable;

public class ExecuableNoSuchOrder  implements Executable {
    private OrderService orderService;

    public ExecuableNoSuchOrder(OrderService orderService) {
        this.orderService = orderService;
    }
    @Override
    public void execute() throws Throwable {
        orderService.getOrder(1L);
    }
}
