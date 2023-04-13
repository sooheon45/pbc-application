package application.external;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Date;
@FeignClient(name = "Storage", url = "${api.url.Storage}")
public interface StorageService {
    @RequestMapping(method= RequestMethod.PUT, path="/storages/{id}/increasestock")
    public void increaseStock(@PathVariable("id")  , @RequestBody IncreaseStockCommand increaseStockCommand );
    @RequestMapping(method= RequestMethod.PUT, path="/storages/{id}/decreasestock")
    public void decreaseStock(@PathVariable("id")  , @RequestBody DecreaseStockCommand decreaseStockCommand );
}