package deliveryapp.domain;

import deliveryapp.domain.*;
import deliveryapp.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;

    public OrderPlaced(Delivery aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
