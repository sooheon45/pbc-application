package application.domain;

import application.OrderApplication;
import application.domain.OrderCanceled;
import application.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemId;

    private Integer qty;

    private String userId;

    private String address;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        application.external.DecreaseStockCommand decreaseStockCommand = new application.external.DecreaseStockCommand();
        // mappings goes here
        OrderApplication.applicationContext
            .getBean(application.external.StorageService.class)
            .decreaseStock(/* get???(), */decreaseStockCommand);

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        application.external.IncreaseStockCommand increaseStockCommand = new application.external.IncreaseStockCommand();
        // mappings goes here
        OrderApplication.applicationContext
            .getBean(application.external.StorageService.class)
            .increaseStock(/* get???(), */increaseStockCommand);

        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
