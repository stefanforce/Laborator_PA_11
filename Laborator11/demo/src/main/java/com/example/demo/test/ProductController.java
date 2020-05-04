package com.example.demo.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final List<Integer> products = new ArrayList<>();
    public ProductController() {
        products.add(1);
        products.add(Integer.valueOf("2"));
    }
    @GetMapping
    public List<Integer> getProducts() {
        return products;
    }
    @GetMapping("/count")
    public int countProducts() {
        return products.size();
    }
    @GetMapping("/{id}")
    public Integer getProduct(@PathVariable("id") int id) {
        return products.stream()
                .filter(p -> p == id).findFirst().orElse(null);
    }

    @PostMapping
    public int createProduct(@RequestParam String name) {
        int id = 1 + products.size();
        products.add(Integer.valueOf(name));
        return id;
    }

    @PostMapping(value = "/obj", consumes="application/json")
    public ResponseEntity<String>
    createProduct(@RequestBody Integer product) {
        products.add(product);
        return new ResponseEntity<>(
                "Product created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id, @RequestParam String name) {
        Integer product = products.get(id);
        if (product == null) {
            return new ResponseEntity<>(
                    "Product not found", HttpStatus.NOT_FOUND); //or GONE
        }
        return new ResponseEntity<>(
                "Product updated successsfully", HttpStatus.OK);
    }
}