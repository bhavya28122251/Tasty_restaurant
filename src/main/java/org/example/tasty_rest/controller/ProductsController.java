package org.example.tasty_rest.controller;

import lombok.RequiredArgsConstructor;
import org.example.tasty_rest.dto.ProductsRequest;
import org.example.tasty_rest.entity.Products;
import org.example.tasty_rest.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    // final : it will create only one object in whole project container, when we run the project
    private final ProductsService productsService;

    @PostMapping("/add")// for post request
    public ResponseEntity<Products> addProduct(@RequestBody @Valid ProductsRequest request) {
        System.out.println("----------Create Controller----------");
        return ResponseEntity.ok(productsService.addProduct(request));
    }

    @GetMapping({"/get", "/get/{id}"})
    public ResponseEntity<?> getProducts(@PathVariable(required = false) Long id) {
        if (id != null) {

            Products product = productsService.getProductById(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {

            List<Products> allProducts = productsService.getAllProducts();
            return ResponseEntity.ok(allProducts);
        }
    }

    @GetMapping({"/get_top_2"})
    public List<Products> getTop2Products(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productsService.getTop2ProductsInPriceRange(minPrice, maxPrice);
    }
}
