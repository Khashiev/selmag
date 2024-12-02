package pro.nazirka.selmag.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.nazirka.selmag.manager.entity.Product;
import pro.nazirka.selmag.manager.payload.UpdateProductPayload;
import pro.nazirka.selmag.manager.service.ProductService;


@Controller
@RequestMapping("catalog/products/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productService.findProduct(productId).orElseThrow();
    }

    @GetMapping
    public String getProduct() {
        return "catalog/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalog/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute("product") Product product, UpdateProductPayload payload) {
        this.productService.updateProduct(product.getId(), payload.title(), payload.details());
        return "redirect:/catalog/products/%d".formatted(product.getId());
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productService.delete(product.getId());
        return "redirect:/catalog/products/list";
    }
}
