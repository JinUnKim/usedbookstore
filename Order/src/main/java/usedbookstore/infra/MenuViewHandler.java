package usedbookstore.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import usedbookstore.config.kafka.KafkaProcessor;
import usedbookstore.domain.*;

@Service
public class MenuViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MenuRepository menuRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInventoryRegistered_then_CREATE_1(
        @Payload InventoryRegistered inventoryRegistered
    ) {
        try {
            if (!inventoryRegistered.validate()) return;

            // view 객체 생성
            Menu menu = new Menu();
            // view 객체에 이벤트의 Value 를 set 함
            menu.setBookId(inventoryRegistered.getBookId());
            menu.setBookName(inventoryRegistered.getBookName());
            menu.setQty(inventoryRegistered.getQty());
            menu.setPrice(inventoryRegistered.getPrice());
            // view 레파지 토리에 save
            menuRepository.save(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInventoryIncreased_then_UPDATE_1(
        @Payload InventoryIncreased inventoryIncreased
    ) {
        try {
            if (!inventoryIncreased.validate()) return;
            // view 객체 조회

            List<Menu> menuList = menuRepository.findByBookId(
                inventoryIncreased.getBookId()
            );
            for (Menu menu : menuList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                menu.setQty(inventoryIncreased.getQty());
                // view 레파지 토리에 save
                menuRepository.save(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInventoryDecreased_then_UPDATE_2(
        @Payload InventoryDecreased inventoryDecreased
    ) {
        try {
            if (!inventoryDecreased.validate()) return;
            // view 객체 조회

            List<Menu> menuList = menuRepository.findByBookId(
                inventoryDecreased.getBookId()
            );
            for (Menu menu : menuList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                menu.setQty(inventoryDecreased.getQty());
                // view 레파지 토리에 save
                menuRepository.save(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
