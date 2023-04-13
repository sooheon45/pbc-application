package storeapp.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import storeapp.StorageApplication;
import storeapp.domain.StockDecreased;
import storeapp.domain.StockIncreased;

@Entity
@Table(name = "Storage_table")
@Data
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String qty;

    @PostPersist
    public void onPostPersist() {
        StockIncreased stockIncreased = new StockIncreased(this);
        stockIncreased.publishAfterCommit();
    }

    @PostPersist
    public void onPostPersist() {
        StockDecreased stockDecreased = new StockDecreased(this);
        stockDecreased.publishAfterCommit();
    }

    public static StorageRepository repository() {
        StorageRepository storageRepository = applicationContext()
            .getBean(StorageRepository.class);
        return storageRepository;
    }

    public static ApplicationContext applicationContext() {
        return StorageApplication.applicationContext;
    }

    public void increaseStock(IncreaseStockCommand increaseStockCommand) {
        // implement the business logics here:

    }

    public void decreaseStock(DecreaseStockCommand decreaseStockCommand) {
        // implement the business logics here:

    }
}
