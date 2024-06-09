package com.example.chocolate.controllers;

import com.example.chocolate.entities.*;
import com.example.chocolate.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rawmaterials")
public class RawMaterialController {
    @Autowired
    private RawMaterialService rawMaterialService;

    @GetMapping
    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialService.getAllRawMaterials();
    }

    @GetMapping("/{id}")
    public RawMaterial getRawMaterialById(@PathVariable Long id) {
        return rawMaterialService.getRawMaterialById(id);
    }

    @PostMapping
    public RawMaterial createRawMaterial(@RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.saveRawMaterial(rawMaterial);
    }

    @PutMapping("/{id}")
    public RawMaterial updateRawMaterial(@PathVariable Long id, @RequestBody RawMaterial rawMaterialDetails) {
        RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(id);
        rawMaterial.setName(rawMaterialDetails.getName());
        rawMaterial.setQuantity(rawMaterialDetails.getQuantity());
        rawMaterial.setSupplier(rawMaterialDetails.getSupplier());
        return rawMaterialService.saveRawMaterial(rawMaterial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable Long id) {
        rawMaterialService.deleteRawMaterial(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<RawMaterial> searchRawMaterialsByName(@RequestParam String name) {
        return rawMaterialService.searchRawMaterialsByName(name);
    }

    @GetMapping("/filter")
    public List<RawMaterial> filterRawMaterialsByQuantity(@RequestParam int quantity) {
        return rawMaterialService.filterRawMaterialsByQuantity(quantity);
    }

    @GetMapping("/sort")
    public List<RawMaterial> sortRawMaterialsByName() {
        return rawMaterialService.sortRawMaterialsByName();
    }
}
