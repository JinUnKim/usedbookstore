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
    private Long orderId;

    private Long bookId;

    private Integer qty;

    private String orderStatus;

    private Integer price;

    @PostPersist
    public void onPostPersist() {
        Ordered ordered = new Ordered(this);
        ordered.publishAfterCommit();

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
