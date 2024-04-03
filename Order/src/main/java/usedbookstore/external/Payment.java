package usedbookstore.external;

import java.util.Date;
import lombok.Data;

@Data
public class Payment {

    private Long id;
    private Long paymentId;
    private Long orderId;
    private Long bookId;
    private Integer price;
    private String paymentStatus;
    private String orderStatus;
    private Integer qty;
}
