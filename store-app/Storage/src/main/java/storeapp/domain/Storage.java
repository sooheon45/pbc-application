package storeapp.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
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

        StockDecreased stockDecreased = new StockDecreased(this);
        stockDecreased.publishAfterCommit();
    }

    public static StorageRepository repository() {
        StorageRepository storageRepository = StorageApplication.applicationContext.getBean(
            StorageRepository.class
        );
        return storageRepository;
    }

    public void increaseStock(IncreaseStockCommand increaseStockCommand) {}

    public void decreaseStock(DecreaseStockCommand decreaseStockCommand) {}
}
