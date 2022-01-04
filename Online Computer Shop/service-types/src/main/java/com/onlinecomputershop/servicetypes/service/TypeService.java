package com.onlinecomputershop.servicetypes.service;

import com.onlinecomputershop.servicetypes.repo.TypeRepo;
import com.onlinecomputershop.servicetypes.repo.model.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TypeService {
    private final TypeRepo typeRepo;

    public List<Type> fetchAll(){
        return typeRepo.findAll();

    }

    public Type fetchOne(long id){
        final Optional<Type> maybetype = typeRepo.findById(id);

        if (maybetype.isEmpty()) throw new IllegalArgumentException("type not found");
        else return maybetype.get();
    }

    public long create(String name, String description){
        final Type newtype = new Type(name, description);

        final Type savedtype = typeRepo.save(newtype);
        return savedtype.getId();
    }

    public long update(long id, com.onlinecomputershop.servicetypes.api.dto.Type type) throws IllegalArgumentException {
        final Optional<Type> maybetype = typeRepo.findById(id);
        final String name = type.getName();
        final String description = type.getDescription();

        if (maybetype.isPresent()) {
            final Type existedtype = maybetype.get();
            if (name != null && !name.isBlank()) existedtype.setName(name);
            if (description != null && !description.isBlank()) existedtype.setDescription(description);
            typeRepo.save(existedtype);
        } else throw new IllegalArgumentException("Invalid device ID");
        return id;
    }

    public void delete(long id) throws IllegalArgumentException {
        typeRepo.deleteById(id);
    }
}
