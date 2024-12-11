package pro.nazirka.selmag.catalog.repository;

import org.springframework.data.repository.CrudRepository;
import pro.nazirka.selmag.catalog.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
