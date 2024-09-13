package app.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import sales.OrderManagerGrpc.OrderManagerBlockingStub;
import sales.OrderManagerOuterClass.CustomerInput;
import sales.OrderManagerOuterClass.OrderInput;

@RestController
public class SalesAgentController {
    
    @GrpcClient("sales")
    private OrderManagerBlockingStub remote;

    @GetMapping("/api/sales/{id}")
    public ResponseEntity<List<OrderResource>> readOrders(@PathVariable("id") String customerId) {
        var orders = new ArrayList<OrderResource>();
        var request = CustomerInput.newBuilder()
                        .setCustomerCode(customerId)
                        .build();
        var reply = remote.fetchOrders(request);
        reply.forEachRemaining(message -> {
            var order = new OrderResource();
            order.productNo = message.getItemCode();
            order.quantity = message.getItemCount();
            order.orderDate = message.getConfirmationDate();
            orders.add(order);
        });
        return orders.size() > 0
                ? ResponseEntity.ok(orders)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/api/sales")
    public ResponseEntity<OrderResource> createOrder(@RequestBody OrderResource order) {
        try{
            var request = OrderInput.newBuilder()
                            .setCustomerCode(order.customerId)
                            .setItemCode(order.productNo)
                            .setItemCount(order.quantity)
                            .build();
            var reply = remote.placeOrder(request);
            order.orderNo = reply.getConfirmationCode();
            return ResponseEntity.ok(order);
        }catch(StatusRuntimeException e){
            Logger.getLogger(getClass().getName()).severe(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }
}
