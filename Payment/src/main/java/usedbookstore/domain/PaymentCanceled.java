package usedbookstore.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import usedbookstore.domain.*;
import usedbookstore.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PaymentCanceled extends AbstractEvent {

    private Long paymentId;
    private Long orderId;
    private Long bookId;
    private Integer price;
    private String orderStatus;
    private Integer qty;

    public PaymentCanceled(Payment aggregate) {
        super(aggregate);
    }

    public PaymentCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
