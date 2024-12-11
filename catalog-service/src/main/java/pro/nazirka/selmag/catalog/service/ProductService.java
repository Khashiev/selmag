package pro.nazirka.selmag.catalog.service;

import pro.nazirka.selmag.catalog.entity.Product;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> findAllProducts(String filter);

    Product createProduct(String title, String details);

    Optional<Product> findProduct(int productId);

    void updateProduct(Integer id, String title, String details);

    void delete(Integer id);
}
