package persistence;

import entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductPersistence extends CrudRepository<Product,Integer> {
}
