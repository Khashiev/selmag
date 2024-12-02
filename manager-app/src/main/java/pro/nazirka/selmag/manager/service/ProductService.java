package pro.nazirka.selmag.manager.service;

import pro.nazirka.selmag.manager.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
}
