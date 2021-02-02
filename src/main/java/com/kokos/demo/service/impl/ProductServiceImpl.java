package com.kokos.demo.service.impl;

import com.kokos.demo.dtos.ProductDTO;
import com.kokos.demo.models.Product;
import com.kokos.demo.repositoties.ProductRepository;
import com.kokos.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductDTO> findAll() {
        List<ProductDTO> result = new ArrayList<>();
        productRepository.findAll().stream().map(l -> result.add(ProductDTO.build(l)));
        return result;
    }

    @Override
    @Transactional
    public ProductDTO findById(Long id) {
        Product product = productRepository.getOne(id);
        if (product == null) {
            throw new NoSuchElementException("Product " + id + " not found");
        }
        return ProductDTO.build(product);
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productRepository.saveAndFlush(productDTO.getProduct());
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product not save");
        }
        return ProductDTO.build(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductDTO findByName(String name) {
        Product product = productRepository.findByName(name);
        if (product == null) {
            throw new NoSuchElementException("Product with name: " + name + " not found");
        }
        return ProductDTO.build(product);
    }

}
