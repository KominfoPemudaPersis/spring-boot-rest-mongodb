package com.kominfo.halaqohit.springbootdatajpamongodb.controller;

import com.kominfo.halaqohit.springbootdatajpamongodb.entity.Pets;
import com.kominfo.halaqohit.springbootdatajpamongodb.repository.PetsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-data-jpa-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-17
 * Time: 05:52
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/pets")
public class PetsController {
    @Autowired
    private PetsRepository repository;

    @GetMapping
    public List<Pets> getAllPets() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Pets getPetById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @PutMapping("/{id}")
    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid
    @RequestBody Pets pets) {
        pets.set_id(id);
        repository.save(pets);
    }

    @PostMapping
    public Pets createPet(@Valid @RequestBody Pets pets) {
        pets.set_id(ObjectId.get());
        repository.save(pets);
        return pets;
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
