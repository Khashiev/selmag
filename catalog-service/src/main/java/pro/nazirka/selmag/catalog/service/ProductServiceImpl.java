package pro.nazirka.selmag.catalog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.nazirka.selmag.catalog.entity.Product;
import pro.nazirka.selmag.catalog.repository.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> findAllProducts(String filter) {
        if (filter != null && !filter.isBlank()) {
            return this.productRepository.findAllByTitleLikeIgnoreCase("%" + filter + "%");
        } else {
            return this.productRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Product createProduct(String title, String details) {
        return this.productRepository.save(new Product(null, title, details));
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    @Transactional
    public void updateProduct(Integer id, String title, String details) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        this.productRepository.deleteById(id);
    }
}
