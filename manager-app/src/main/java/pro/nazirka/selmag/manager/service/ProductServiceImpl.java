package pro.nazirka.selmag.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.nazirka.selmag.manager.entity.Product;
import pro.nazirka.selmag.manager.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }
}
