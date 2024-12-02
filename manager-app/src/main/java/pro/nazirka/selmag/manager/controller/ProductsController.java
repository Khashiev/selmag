package pro.nazirka.selmag.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pro.nazirka.selmag.manager.entity.Product;
import pro.nazirka.selmag.manager.controller.payload.NewProductPayload;
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
    public String getNewProductPage() {
        return "catalog/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(@Valid NewProductPayload payload,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());

            return "catalog/products/new_product";
        } else {
            Product product = this.productService.createProduct(payload.title(), payload.details());
            return "redirect:/catalog/products/%d".formatted(product.getId());
        }
    }
}
