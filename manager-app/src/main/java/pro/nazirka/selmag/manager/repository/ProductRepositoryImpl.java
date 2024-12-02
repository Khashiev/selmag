package pro.nazirka.selmag.manager.repository;

import org.springframework.stereotype.Repository;
import pro.nazirka.selmag.manager.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public ProductRepositoryImpl() {
        IntStream.range(1, 4)
                .forEach(i -> this.products.add(new Product(i, "Товар N%d".formatted(i),
                        "Описание товара N%d".formatted(i))));
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }
}
