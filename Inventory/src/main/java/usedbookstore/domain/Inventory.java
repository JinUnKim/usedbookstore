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
        //implement business logic here:

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        InventoryIncreased inventoryIncreased = new InventoryIncreased(inventory);
        inventoryIncreased.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(inventory->{
            
            inventory // do something
            repository().save(inventory);

            InventoryIncreased inventoryIncreased = new InventoryIncreased(inventory);
            inventoryIncreased.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increaseInventory(BookRegistered bookRegistered) {
        //implement business logic here:

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        InventoryIncreased inventoryIncreased = new InventoryIncreased(inventory);
        inventoryIncreased.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(bookRegistered.get???()).ifPresent(inventory->{
            
            inventory // do something
            repository().save(inventory);

            InventoryIncreased inventoryIncreased = new InventoryIncreased(inventory);
            inventoryIncreased.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void decreaseInventory(Paid paid) {
        //implement business logic here:

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        InventoryDecreased inventoryDecreased = new InventoryDecreased(inventory);
        inventoryDecreased.publishAfterCommit();
        OutOfInventory outOfInventory = new OutOfInventory(inventory);
        outOfInventory.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(inventory->{
            
            inventory // do something
            repository().save(inventory);

            InventoryDecreased inventoryDecreased = new InventoryDecreased(inventory);
            inventoryDecreased.publishAfterCommit();
            OutOfInventory outOfInventory = new OutOfInventory(inventory);
            outOfInventory.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
