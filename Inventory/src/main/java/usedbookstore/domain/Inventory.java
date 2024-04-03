package usedbookstore.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import usedbookstore.InventoryApplication;
import usedbookstore.domain.InventoryDecreased;
import usedbookstore.domain.InventoryIncreased;
import usedbookstore.domain.InventoryRegistered;
import usedbookstore.domain.OutOfInventory;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long bookId;

    private String bookName;

    private Integer qty;

    private Integer price;

    @PostPersist
    public void onPostPersist() {
        InventoryIncreased inventoryIncreased = new InventoryIncreased(this);
        inventoryIncreased.publishAfterCommit();

        InventoryDecreased inventoryDecreased = new InventoryDecreased(this);
        inventoryDecreased.publishAfterCommit();

        OutOfInventory outOfInventory = new OutOfInventory(this);
        outOfInventory.publishAfterCommit();

        InventoryRegistered inventoryRegistered = new InventoryRegistered(this);
        inventoryRegistered.publishAfterCommit();
    }

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void increaseInventory(PaymentCanceled paymentCanceled) {
        repository().findById(Long.valueOf(paymentCanceled.getBookId())).ifPresent(inventory->{
            
            inventory.setQty(inventory.getQty() + paymentCanceled.getQty()); // do something
            repository().save(inventory);

         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increaseInventory(BookRegistered bookRegistered) {
        //implement business logic here:

        repository().findById(Long.valueOf(bookRegistered.getBookId())).ifPresent(inventory->{
            
            inventory.setQty(inventory.getQty() + bookRegistered.getQty()); // do something
            repository().save(inventory);


         });
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void decreaseInventory(Paid paid) {

        repository().findById(Long.valueOf(paid.getBookId())).ifPresent(inventory->{
            if(inventory.getQty() >= paid.getQty()){
                inventory.setQty(inventory.getQty() - paid.getQty()); // do something
                repository().save(inventory);
            }else{
                OutOfInventory outOfInventory = new OutOfInventory(inventory);
                outOfInventory.setOrderId(paid.getOrderId()); 
                outOfInventory.publishAfterCommit();
            }

         });
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
