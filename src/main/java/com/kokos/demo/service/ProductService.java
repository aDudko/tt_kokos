package com.kokos.demo.service;

import com.kokos.demo.dtos.ProductDTO;
import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id);

    void deleteById(Long id);

    List<ProductDTO> findAll();

    ProductDTO save(ProductDTO productDTO);

    ProductDTO findByName(String name);

}
