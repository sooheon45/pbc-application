package application.domain;

import application.domain.*;
import application.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String itemId;
    private Integer qty;
    private String userId;
    private String address;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
