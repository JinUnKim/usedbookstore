package usedbookstore.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import usedbookstore.SaleApplication;
import usedbookstore.domain.BookRegistered;

@Entity
@Table(name = "Sale_table")
@Data
//<<< DDD / Aggregate Root
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long saleId;

    private Long bookId;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {
        BookRegistered bookRegistered = new BookRegistered(this);
        bookRegistered.publishAfterCommit();
    }

    public static SaleRepository repository() {
        SaleRepository saleRepository = SaleApplication.applicationContext.getBean(
            SaleRepository.class
        );
        return saleRepository;
    }
}
//>>> DDD / Aggregate Root
