package pro.nazirka.selmag.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.nazirka.selmag.manager.entity.Product;
import pro.nazirka.selmag.manager.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String details) {
        return this.productRepository.save(new Product(null, title, details));
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        return this.productRepository.findById(productId);
    }
}
