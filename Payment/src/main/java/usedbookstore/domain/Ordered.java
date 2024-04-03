package usedbookstore.domain;

import java.util.*;
import lombok.*;
import usedbookstore.domain.*;
import usedbookstore.infra.AbstractEvent;

@Data
@ToString
public class Ordered extends AbstractEvent {

    private Long orderId;
    private Long bookId;
    private Integer qty;
    private String orderStatus;
    private Integer price;
}
