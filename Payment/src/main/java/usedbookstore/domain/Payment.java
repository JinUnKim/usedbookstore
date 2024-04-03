package usedbookstore.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import usedbookstore.PaymentApplication;
import usedbookstore.domain.PaymentCanceled;

@Entity
@Table(name = "Payment_table")
@Data
//<<< DDD / Aggregate Root
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long paymentId;

    private Long orderId;

    private Long bookId;

    private Integer price;

    private String paymentStatus;

    private String orderStatus;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {
        PaymentCanceled paymentCanceled = new PaymentCanceled(this);
        paymentCanceled.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    //<<< Clean Arch / Port Method
    public static void pay(Ordered ordered) {
        //implement business logic here:
        Payment payment = new Payment();
        
        payment.setOrderStatus("결제완료");
        payment.setPaymentStatus("결제완료");
        payment.setOrderId(ordered.getOrderId() );
        payment.setBookId(ordered.getBookId() );
        payment.setQty(ordered.getQty() );
        payment.setPrice(ordered.getPrice() );

        Paid paid = new Paid(payment);
        paid.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void cancelPayment(OrderCancelled orderCancelled) {
        Payment payment = new Payment();
        if(orderCancelled.getOrderStatus().equals("재고부족으로 인한 취소")){
            System.out.println(orderCancelled.getOrderId() +" 재고부족으로 결제취소 완료");
        }else{
            payment.setOrderStatus("결제취소");
            payment.setPaymentStatus("결제취소");
            payment.setOrderId(orderCancelled.getOrderId() );
            payment.setBookId(orderCancelled.getBookId() );
            payment.setQty(orderCancelled.getQty() );
            payment.setPrice(orderCancelled.getPrice() );

            Paid paid = new Paid(payment);
            paid.publishAfterCommit();
        }
        

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
