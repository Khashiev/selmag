package pro.nazirka.selmag.manager.repository;

import pro.nazirka.selmag.manager.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
