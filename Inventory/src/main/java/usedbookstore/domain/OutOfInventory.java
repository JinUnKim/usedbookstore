package usedbookstore.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import usedbookstore.domain.*;
import usedbookstore.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OutOfInventory extends AbstractEvent {

    private Long id;
    private Long bookId;
    private String bookName;
    private Integer qty;
    private Integer price;
    private Long orderId;

    public OutOfInventory(Inventory aggregate) {
        super(aggregate);
    }

    public OutOfInventory() {
        super();
    }
}
//>>> DDD / Domain Event
