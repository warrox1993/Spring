package be.bstorm.java.freemarker.pl.controllers;

import be.bstorm.java.freemarker.dal.ProductMock;
import be.bstorm.java.freemarker.dal.entities.ProductEntity;
import be.bstorm.java.freemarker.pl.models.product.ProductAddForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.FlashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductMock mock;

    @GetMapping(path = {"", "/list"})
    public String indexAction(Model model) {
        model.addAttribute("products", mock.getProducts());
        return "products/list";
    }

    @GetMapping(path = {"/{id:[0-9]+}", "/detail/{id}", "/edit/{id:[0-9]+}"})
    public String detailAction(Model model, @PathVariable() Long id) {
        try {
            ProductEntity entity = mock.getProduct(id.intValue());
            model.addAttribute("product", entity);
            return "products/edit";
        } catch (Exception e) {
            model.addAttribute("error", "No product found with id: " + id);
            return "redirect:/products";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/add")
    public String addAction(Model model) {
        return "products/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/add")
    public String addAction(
            Model model,
            @Validated ProductAddForm form,
            BindingResult bindingResult
    ) {
        mock.addProduct(form.toEntity());

        return "redirect:/products";
    }

    @GetMapping(path = {"/remove/{id}"})
    public String removeAction(@PathVariable() Long id) {
        mock.deleteProduct(id.intValue());
        return "redirect:/products";
    }
}
