package app.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.transaction.annotation.Transactional;

import app.data.CounterEntity;
import app.data.OrderEntity;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import net.devh.boot.grpc.server.service.GrpcService;
import sales.OrderManagerGrpc.OrderManagerImplBase;
import sales.OrderManagerOuterClass.CustomerInput;
import sales.OrderManagerOuterClass.CustomerOrder;
import sales.OrderManagerOuterClass.OrderInput;
import sales.OrderManagerOuterClass.OrderStatus;

@GrpcService
@Transactional
public class OrderManagerService extends OrderManagerImplBase {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void fetchOrders(CustomerInput request, StreamObserver<CustomerOrder> responseObserver) {
        em.createQuery("SELECT e FROM OrderEntity e WHERE e.customerId = ?1", OrderEntity.class)
            .setParameter(1, request.getCustomerCode())
            .getResultStream()
            .forEach(entity -> {
                var message = CustomerOrder.newBuilder()
                    .setItemCode(entity.getProductNo())
                    .setItemCount(entity.getQuantity())
                    .setConfirmationDate(entity.orderDateAsString())
                    .build();
                responseObserver.onNext(message);
            }); 
        responseObserver.onCompleted();
    }

    @Override
    public void placeOrder(OrderInput request, StreamObserver<OrderStatus> responseObserver) {
        try{
            int orderNo = em.find(CounterEntity.class, "orders").nextValue();
            var entity = new OrderEntity();
            entity.setOrderNo(orderNo);
            entity.setOrderDate(Timestamp.from(Instant.now()));
            entity.setCustomerId(request.getCustomerCode());
            entity.setProductNo(request.getItemCode());
            entity.setQuantity(request.getItemCount());
            em.persist(entity);
            em.flush();
            var reply = OrderStatus.newBuilder()
                .setConfirmationCode(orderNo)
                .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }catch(PersistenceException e){
            var fault = Status.INTERNAL.withDescription("Order Failed").asRuntimeException();
            responseObserver.onError(fault);
        }
    }
    
    
}
