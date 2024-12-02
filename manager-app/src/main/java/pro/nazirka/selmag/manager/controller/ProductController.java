package pro.nazirka.selmag.manager.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.nazirka.selmag.manager.entity.Product;
import pro.nazirka.selmag.manager.payload.UpdateProductPayload;
import pro.nazirka.selmag.manager.service.ProductService;

import java.util.Locale;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("catalog/products/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("catalog.errors.product.not_found"));
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

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(
            NoSuchElementException exception,
            Model model,
            HttpServletResponse response,
            Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", this.messageSource
                .getMessage(exception.getMessage(), new Object[]{}, exception.getMessage(), locale));
        return "errors/404";
    }
}
