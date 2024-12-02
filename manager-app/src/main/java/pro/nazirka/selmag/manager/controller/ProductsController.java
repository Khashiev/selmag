package pro.nazirka.selmag.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.nazirka.selmag.manager.entity.Product;
import pro.nazirka.selmag.manager.payload.NewProductPayload;
import pro.nazirka.selmag.manager.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalog/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage(Model model) {
        return "catalog/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload) {
        Product product = this.productService.createProduct(payload.title(), payload.details());
        return "redirect:/catalog/products/%d".formatted(product.getId());
    }
}
