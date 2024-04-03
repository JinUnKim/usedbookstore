package usedbookstore.external;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class PayCommand {

    @Id
    private Long id;

    private Long orderId;
    private Long bookId;
    private Integer price;
    private String paymentStatus;
    private String orderStatus;
    private Integer qty;
}
