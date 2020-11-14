package com.kokos.demo.controllers;

import com.kokos.demo.models.Product;
import com.kokos.demo.repositoties.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Validated
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/product/new")
    public String newProduct(Model model) {
        return "product_create";
    }

    @PostMapping("/product/new")
    public String createNewProduct(
            @RequestParam String title,
            @RequestParam Integer cost,
            @RequestParam String location,
            @RequestParam String description,
            Model model
    ) {
        Product product = new Product(title, description, cost, location);
        productRepository.save(product);
        return "redirect:/";
    }

}
