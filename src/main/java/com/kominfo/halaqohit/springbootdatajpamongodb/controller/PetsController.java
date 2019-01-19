package com.kominfo.halaqohit.springbootdatajpamongodb.controller;

import com.kominfo.halaqohit.springbootdatajpamongodb.entity.Pets;
import com.kominfo.halaqohit.springbootdatajpamongodb.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Pets> getPetById(@PathVariable("id") String id) {
        Optional<Pets> petData = repository.findById(id);
        if (petData.isPresent()) {
            return new ResponseEntity<>(petData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public void modifyPetById(@PathVariable("id") String id, @Valid
    @RequestBody Pets pets) {
        pets.setId(id);
        repository.save(pets);
    }

    @PostMapping
    public Pets createPet(@Valid @RequestBody Pets pets) {
        repository.save(pets);
        return pets;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>("Pet has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllPets() {
        try {
            repository.deleteAll();
        } catch (Exception e) {
            return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>("Pets has been deleted!", HttpStatus.OK);
    }
}
