package com.onlinecomputershop.servicebrands.service;


import com.onlinecomputershop.servicebrands.repo.BrandRepo;
import com.onlinecomputershop.servicebrands.repo.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepo brandRepo;

    public List<Brand> fetchAll(){
        return brandRepo.findAll();

    }

    public Brand fetchOne(long id){
        final Optional<Brand> maybebrand = brandRepo.findById(id);

        if (maybebrand.isEmpty()) throw new IllegalArgumentException("brand not found");
        else return maybebrand.get();
    }

    public long create(String name, String description){
        final Brand newbrand = new Brand(name, description);

        final Brand savedbrand = brandRepo.save(newbrand);
        return savedbrand.getId();
    }

    public long update(long id, com.onlinecomputershop.servicebrands.api.dto.Brand brand) throws IllegalArgumentException {
        final Optional<Brand> maybebrand = brandRepo.findById(id);
        final String name = brand.getName();
        final String description = brand.getDescription();

        if (maybebrand.isPresent()) {
            final Brand existedbrand = maybebrand.get();
            if (name != null && !name.isBlank()) existedbrand.setName(name);
            if (description != null && !description.isBlank()) existedbrand.setDescription(description);
            brandRepo.save(existedbrand);
        } else throw new IllegalArgumentException("Invalid device ID");
        return id;
    }

    public void delete(long id) throws IllegalArgumentException {
        brandRepo.deleteById(id);
    }


}
