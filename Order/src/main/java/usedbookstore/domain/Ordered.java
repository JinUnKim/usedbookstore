package usedbookstore.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import usedbookstore.domain.*;
import usedbookstore.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Ordered extends AbstractEvent {

    private Long orderId;
    private Long bookId;
    private Integer qty;
    private String orderStatus;
    private Integer price;

    public Ordered(Order aggregate) {
        super(aggregate);
    }

    public Ordered() {
        super();
    }
}
//>>> DDD / Domain Event
