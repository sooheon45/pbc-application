package application.external;

import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    /**
     * Fallback
     */
    public Storage getStorage(Long id) {
        Storage storage = new Storage();
        return storage;
    }
}
