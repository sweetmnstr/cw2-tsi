package com.example.chocolate.controllers;

import com.example.chocolate.entities.Store;
import com.example.chocolate.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeService.saveStore(store);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store storeDetails) {
        Store store = storeService.getStoreById(id);
        store.setName(storeDetails.getName());
        store.setLocation(storeDetails.getLocation());
        store.setContactInfo(storeDetails.getContactInfo());
        return storeService.saveStore(store);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Store> searchStoresByName(@RequestParam String name) {
        return storeService.searchStoresByName(name);
    }

    @GetMapping("/sort")
    public List<Store> sortStoresByName() {
        return storeService.sortStoresByName();
    }
}
