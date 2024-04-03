package usedbookstore.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import usedbookstore.OrderApplication;
import usedbookstore.domain.OrderCancelled;
import usedbookstore.domain.Ordered;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long bookId;

    private Integer qty;

    private String orderStatus;

    private Integer price;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        Ordered ordered = new Ordered(this);
        ordered.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }
    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateStatus(OutOfInventory outOfInventory) {
        repository().findById(outOfInventory.getOrderId()).ifPresent(order ->{
            
        order.setOrderStatus("재고부족으로 인한 취소");
        repository().save(order);
    
        OrderCancelled orderCancelled = new OrderCancelled(order);
        orderCancelled.publishAfterCommit();
        
        });

        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderCancelled orderCancelled = new OrderCancelled(order);
        orderCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(outOfInventory.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderCancelled orderCancelled = new OrderCancelled(order);
            orderCancelled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
