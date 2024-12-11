package pro.nazirka.selmag.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.nazirka.selmag.manager.client.BadRequestException;
import pro.nazirka.selmag.manager.client.ProductsRestClient;
import pro.nazirka.selmag.manager.entity.Product;
import pro.nazirka.selmag.manager.controller.payload.NewProductPayload;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/products")
public class ProductsController {
    private final ProductsRestClient productsRestClient;

    @GetMapping("list")
    public String getProductsList(Model model, @RequestParam(name = "filter", required = false) String filter) {
        model.addAttribute("products", this.productsRestClient.findAllProducts(filter));
        model.addAttribute("filter", filter);
        return "catalog/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage() {
        return "catalog/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload,
                                Model model) {
        try {
            Product product = this.productsRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalog/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalog/products/new_product";
        }
    }
}

//@Controller
//@RequiredArgsConstructor
//@RequestMapping("catalog/products")
//public class ProductsController {
//
//    private final ProductsRestClient productsRestClient;
//
//    @GetMapping("list")
//    public String getProductsList(Model model) {
//        model.addAttribute("products", this.productsRestClient.findAllProducts());
//        return "catalog/products/list";
//    }
//
//    @GetMapping("create")
//    public String getNewProductPage() {
//        return "catalog/products/new_product";
//    }
//
//    @PostMapping("create")
//    public String createProduct(NewProductPayload payload,
//                                Model model) {
//        try {
//            Product product = this.productsRestClient.createProduct(payload.title(), payload.details());
//            return "redirect:/catalog/products/%d".formatted(product.id());
//        } catch (BadRequestException exception) {
//            model.addAttribute("payload", payload);
//            model.addAttribute("errors", exception.getErrors());
//            return "catalog/products/new_product";
//        }
//    }
//}
