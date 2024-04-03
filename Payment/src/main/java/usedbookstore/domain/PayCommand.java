package usedbookstore.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PayCommand {

    private Long id;
    private Long orderId;
    private Long bookId;
    private Integer price;
    private String paymentStatus;
    private String orderStatus;
    private Integer qty;
}
