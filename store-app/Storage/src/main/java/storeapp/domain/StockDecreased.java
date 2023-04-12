package storeapp.domain;

import java.util.*;
import lombok.*;
import storeapp.domain.*;
import storeapp.infra.AbstractEvent;

@Data
@ToString
public class StockDecreased extends AbstractEvent {

    private Long id;
    private String qty;

    public StockDecreased(Storage aggregate) {
        super(aggregate);
    }

    public StockDecreased() {
        super();
    }
}
