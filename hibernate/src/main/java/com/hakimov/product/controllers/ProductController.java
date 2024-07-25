package com.hakimov.product.controllers;

import com.hakimov.product.dao.ProductDAO;
import com.hakimov.product.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String getProducts(Model model) {
        model.addAttribute("products", productDAO.getProducts());
        return "products/show_products";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.getProduct(id));
        return "products/show_product";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "products/new";
    }

    @PostMapping()
    public String createProduct(@ModelAttribute("product") @Valid Product product,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "products/new";

        productDAO.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.getProduct(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String updateProduct (@ModelAttribute("product") @Valid Product product,
                                 BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "products/edit";

        productDAO.update(product, id);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productDAO.delete(id);
        return "redirect:/products";
    }

}
