package application.domain;

import application.domain.*;
import application.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private String itemId;
    private Integer qty;
    private String userId;
    private String address;

    public OrderCanceled(Order aggregate) {
        super(aggregate);
    }

    public OrderCanceled() {
        super();
    }
}
