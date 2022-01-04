package com.onlinecomputershop.servicetypes.api;


import com.onlinecomputershop.servicetypes.repo.model.Type;
import com.onlinecomputershop.servicetypes.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/types")
public final class TypeController {
    private final TypeService typeService;

    @GetMapping
    public ResponseEntity<List<Type>> index(){
        final List<Type> types = typeService.fetchAll();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> show(@PathVariable long id) {
        try{
            final Type type = typeService.fetchOne(id);
            return ResponseEntity.ok(type);

        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.onlinecomputershop.servicetypes.api.dto.Type type) {
        final String name = type.getName();
        final String description = type.getDescription();

        final long id = typeService.create(name, description);
        final String location = String.format("/types/%d", id);

        return ResponseEntity.created(URI.create(location)).build();

    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.onlinecomputershop.servicetypes.api.dto.Type type) {
        final String name = type.getName();
        final String description = type.getDescription();

        try{
            typeService.update(id, type);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        typeService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
