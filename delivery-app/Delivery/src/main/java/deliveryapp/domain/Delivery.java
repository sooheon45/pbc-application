package deliveryapp.domain;

import deliveryapp.DeliveryApplication;
import deliveryapp.domain.DeliveryCanceled;
import deliveryapp.domain.DeliveryReturned;
import deliveryapp.domain.DeliveryStarted;
import deliveryapp.domain.OrderCanceled;
import deliveryapp.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.context.ApplicationContext;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    private String userId;

    private Integer qty;

    private String itemId;

    @PostPersist
    public void onPostPersist() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    @PostPersist
    public void onPostPersist() {
        DeliveryCanceled deliveryCanceled = new DeliveryCanceled(this);
        deliveryCanceled.publishAfterCommit();
    }

    @PostPersist
    public void onPostPersist() {
        DeliveryReturned deliveryReturned = new DeliveryReturned(this);
        deliveryReturned.publishAfterCommit();
    }

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PostPersist
    public void onPostPersist() {
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = applicationContext()
            .getBean(DeliveryRepository.class);
        return deliveryRepository;
    }

    public static ApplicationContext applicationContext() {
        return DeliveryApplication.applicationContext;
    }

    public static void startDelivery(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);
        DeliveryStarted deliveryStarted = new DeliveryStarted(delivery);
        deliveryStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);
            DeliveryStarted deliveryStarted = new DeliveryStarted(delivery);
            deliveryStarted.publishAfterCommit();
         });
        */

    }

    public static void cancelDelivery(OrderCanceled orderCanceled) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);
        DeliveryCanceled deliveryCanceled = new DeliveryCanceled(delivery);
        deliveryCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);
            DeliveryCanceled deliveryCanceled = new DeliveryCanceled(delivery);
            deliveryCanceled.publishAfterCommit();
         });
        */

    }
}
