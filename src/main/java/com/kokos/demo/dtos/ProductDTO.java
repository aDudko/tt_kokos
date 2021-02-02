package com.kokos.demo.dtos;

import com.kokos.demo.models.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Product", description = "Продукт")
public class ProductDTO {

    private Long id;
    private String name;
    private String title;
    private String description;
    private Integer cost;
    private String location;

    public static ProductDTO build(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .title(product.getTitle())
                .description(product.getDescription())
                .cost(product.getCost())
                .location(product.getLocation())
                .build();
    }

    public Product getProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setCost(this.cost);
        product.setLocation(this.location);
        return product;
    }

}
