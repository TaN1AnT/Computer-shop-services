package com.onlinecomputershop.servicebrands.api;

import com.onlinecomputershop.servicebrands.repo.model.Brand;
import com.onlinecomputershop.servicebrands.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public final class BrandsController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> index(){
        final List<Brand> brands = brandService.fetchAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> show(@PathVariable long id) {
        try{
            final Brand brand = brandService.fetchOne(id);
            return ResponseEntity.ok(brand);

        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.onlinecomputershop.servicebrands.api.dto.Brand brand) {
        final String name = brand.getName();
        final String description = brand.getDescription();

        final long id = brandService.create(name, description);
        final String location = String.format("/brands/%d", id);

        return ResponseEntity.created(URI.create(location)).build();

    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.onlinecomputershop.servicebrands.api.dto.Brand brand) {
        final String name = brand.getName();
        final String description = brand.getDescription();

        try{
            brandService.update(id, brand);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        brandService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
