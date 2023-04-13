package application.domain;

import application.OrderApplication;
import application.domain.OrderCanceled;
import application.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.context.ApplicationContext;

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
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
        /** TODO:  REST API Call to Storage
        application.external.DecreaseStockCommand decreaseStockCommand = new application.external.DecreaseStockCommand();
        
        // TODO: fill the command properties to invoke below
        
        applicationContext().getBean(application.external.StorageService.class)
           .decreaseStock({TODO: please put the id}, decreaseStockCommand);
        */

    }

    @PostRemove
    public void onPostRemove() {
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
        /** TODO:  REST API Call to Storage
        application.external.IncreaseStockCommand increaseStockCommand = new application.external.IncreaseStockCommand();
        
        // TODO: fill the command properties to invoke below
        
        applicationContext().getBean(application.external.StorageService.class)
           .increaseStock({TODO: please put the id}, increaseStockCommand);
        */

    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = applicationContext()
            .getBean(OrderRepository.class);
        return orderRepository;
    }

    public static ApplicationContext applicationContext() {
        return OrderApplication.applicationContext;
    }
}
