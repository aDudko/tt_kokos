package com.kokos.demo.controllers.v1;

import com.kokos.demo.dtos.ProductDTO;
import com.kokos.demo.responses.ResponsePage;
import com.kokos.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Методы для работы с продуктом", description = "Методы для работы с продуктом")
@RequestMapping(value = "/product", produces = "application/json")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "Список продуктов",
            responses = @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
            }))
    @GetMapping(value = {"/", ""})
    public ResponsePage<ProductDTO> list() {
        List<ProductDTO> products = productService.findAll();
        return new ResponsePage<>(products, true);
    }

    @Operation(summary = "Получение продукта для редактирования просмотра",
            responses = @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
            }))
    @GetMapping("/{id}")
    public ResponsePage<ProductDTO> view (
            @PathVariable(value = "id") Long id
    ) {
        return new ResponsePage<>(productService.findById(id), true);
    }

    @Operation(summary = "Создание продукта",
            responses = @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
            }))
    @PostMapping("/new")
    public ResponsePage<ProductDTO> create(
            @Parameter(description = "Объект product") @RequestBody ProductDTO productDTO
    ) {
        ProductDTO reloadProduct = productService.save(productDTO);
        return new ResponsePage<>(reloadProduct, true);
    }

    @Operation(summary = "Обновление продукта",
            responses = @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
            }))
    @PutMapping("/edit/{id}")
    public ResponsePage<ProductDTO> update(
            @PathVariable(value = "id") Long id,
            @Parameter(description = "Объект product") @RequestBody ProductDTO productDTO
    ) {
        ProductDTO result = productService.findById(id);
        result.setTitle(productDTO.getTitle());
        result.setCost(productDTO.getCost());
        result.setDescription(productDTO.getDescription());
        result.setLocation(productDTO.getLocation());
        return new ResponsePage<>(productService.save(result), true);
    }

    @Operation(summary = "Удаление продукта",
            responses = @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
            }))
    @DeleteMapping("/{id}")
    public ResponsePage<ProductDTO> delete(@PathVariable(value = "id") long id) {
        productService.deleteById(id);
        return new ResponsePage<>(true);
    }

    @Operation(summary = "Поиск продукта по названию",
            responses = @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))
            }))
    @GetMapping("/find")
    public ResponsePage<ProductDTO> findByName(@RequestParam String name) {
        return new ResponsePage<>(productService.findByName(name), true);
    }

}
