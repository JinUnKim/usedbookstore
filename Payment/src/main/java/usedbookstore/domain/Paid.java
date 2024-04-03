package usedbookstore.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import usedbookstore.domain.*;
import usedbookstore.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Paid extends AbstractEvent {

    private Long id;
    private Long paymentId;
    private Long orderId;
    private Long bookId;
    private Integer price;
    private String paymentStatus;
    private String orderStatus;
    private Integer qty;

    public Paid(Payment aggregate) {
        super(aggregate);
    }

    public Paid() {
        super();
    }
}
//>>> DDD / Domain Event
