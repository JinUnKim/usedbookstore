package usedbookstore.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "Menu_table")
@Data
public class Menu {

    private Long bookId;
    private String bookName;
    private Integer qty;
    private Integer price;
}
