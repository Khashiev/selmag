package pro.nazirka.selmag.catalog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pro.nazirka.selmag.catalog.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(name = "Product.findByAllTitleIgnoringCase", nativeQuery = true)
    Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
}
