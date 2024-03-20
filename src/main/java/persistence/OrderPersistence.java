package persistence;

import entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderPersistence extends CrudRepository<Order,Integer> {
}
