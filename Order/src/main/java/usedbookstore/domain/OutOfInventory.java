package usedbookstore.domain;

import java.util.*;
import lombok.*;
import usedbookstore.domain.*;
import usedbookstore.infra.AbstractEvent;

@Data
@ToString
public class OutOfInventory extends AbstractEvent {

    private Long bookId;
    private String bookName;
    private Integer qty;
    private Integer price;
    private Long orderId;
}
