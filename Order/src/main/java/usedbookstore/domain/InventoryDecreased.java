package usedbookstore.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import usedbookstore.infra.AbstractEvent;

@Data
public class InventoryDecreased extends AbstractEvent {

    private Long id;
    private Long bookId;
    private String bookName;
    private Integer qty;
    private Integer price;
}
