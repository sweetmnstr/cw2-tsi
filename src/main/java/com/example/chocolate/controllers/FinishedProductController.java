package com.example.chocolate.controllers;

import com.example.chocolate.entities.FinishedProduct;
import com.example.chocolate.services.FinishedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finishedproducts")
public class FinishedProductController {
    @Autowired
    private FinishedProductService finishedProductService;

    @GetMapping
    public List<FinishedProduct> getAllFinishedProducts() {
        return finishedProductService.getAllFinishedProducts();
    }

    @GetMapping("/{id}")
    public FinishedProduct getFinishedProductById(@PathVariable Long id) {
        return finishedProductService.getFinishedProductById(id);
    }

    @PostMapping
    public FinishedProduct createFinishedProduct(@RequestBody FinishedProduct finishedProduct) {
        return finishedProductService.saveFinishedProduct(finishedProduct);
    }

    @PutMapping("/{id}")
    public FinishedProduct updateFinishedProduct(@PathVariable Long id,
            @RequestBody FinishedProduct finishedProductDetails) {
        FinishedProduct finishedProduct = finishedProductService.getFinishedProductById(id);
        finishedProduct.setName(finishedProductDetails.getName());
        finishedProduct.setQuantity(finishedProductDetails.getQuantity());
        finishedProduct.setProductionDate(finishedProductDetails.getProductionDate());
        finishedProduct.setExpiryDate(finishedProductDetails.getExpiryDate());
        return finishedProductService.saveFinishedProduct(finishedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinishedProduct(@PathVariable Long id) {
        finishedProductService.deleteFinishedProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<FinishedProduct> searchFinishedProductsByName(@RequestParam String name) {
        return finishedProductService.searchFinishedProductsByName(name);
    }

    @GetMapping("/filter")
    public List<FinishedProduct> filterFinishedProductsByQuantity(@RequestParam int quantity) {
        return finishedProductService.filterFinishedProductsByQuantity(quantity);
    }

    @GetMapping("/sort")
    public List<FinishedProduct> sortFinishedProductsByName() {
        return finishedProductService.sortFinishedProductsByName();
    }
}
