package pro.nazirka.selmag.manager.repository;

import org.springframework.stereotype.Repository;
import pro.nazirka.selmag.manager.entity.Product;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

//    public ProductRepositoryImpl() {
//        IntStream.range(1, 4)
//                .forEach(i -> this.products.add(new Product(i, "Товар N%d".formatted(i),
//                        "Описание товара N%d".formatted(i))));
//    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product save(Product product) {
        product.setId(products.size() + 1);
        this.products.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return this.products.stream()
                .filter(product -> Objects.equals(product.getId(), productId))
                .findFirst();
    }
}
