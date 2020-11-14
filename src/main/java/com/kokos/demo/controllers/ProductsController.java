package com.kokos.demo.controllers;

import com.kokos.demo.models.Product;
import com.kokos.demo.repositoties.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable(value = "id") long id, Model model) {
        if(!productRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> list = new ArrayList<>();
        product.ifPresent(list::add);
        model.addAttribute("product", list);
        return "product_edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(
            @PathVariable(value = "id") long id,
            @RequestParam String title,
            @RequestParam Integer cost,
            @RequestParam String location,
            @RequestParam String description,
            Model model
    ) {
        if(!productRepository.existsById(id)) {
            return "redirect:/";
        }
        Product product = productRepository.findById(id).orElseThrow();
        product.setTitle(title);
        product.setCost(cost);
        product.setLocation(location);
        product.setDescription(description);
        productRepository.save(product);
        model.addAttribute("product", product);
        return "product_details";
    }

    @GetMapping("/{id}")
    public String productDetails(@PathVariable(value = "id") long id, Model model) {
        if(!productRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> list = new ArrayList<>();
        product.ifPresent(list::add);
        model.addAttribute("product", list);
        return "product_details";
    }

    @PostMapping("/{id}/remove")
    public String deleteProduct(@PathVariable(value = "id") long id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/";
    }

}
