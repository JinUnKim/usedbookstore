package usedbookstore.domain;

import java.util.*;
import lombok.*;
import usedbookstore.domain.*;
import usedbookstore.infra.AbstractEvent;

@Data
@ToString
public class BookRegistered extends AbstractEvent {

    private Long id;
    private Long saleId;
    private Long bookId;
    private Integer qty;
    private String userId;
    private String status;
}
