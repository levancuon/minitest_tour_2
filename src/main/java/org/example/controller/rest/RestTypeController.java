package org.example.controller.rest;

import org.example.model.DTO.TypeDTO;
import org.example.model.Type;
import org.example.service.ITypeService;
import org.example.service.imp.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/type")
public class RestTypeController {
    @Autowired
    private final ITypeService typeService;

    public RestTypeController(ITypeService typeService) {
        this.typeService = new TypeService();
    }

    @GetMapping
    public ResponseEntity<Iterable<Type>> findAllType() {
        Iterable<Type> types = typeService.findAll();
        System.out.println(types);
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(@PathVariable("id") Long id, @RequestBody Type type) {
        Optional<Type> typeOptional = typeService.findById(id);
        if (!typeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        type.setId(typeOptional.get().getId());
        typeService.save(type);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> findById(@PathVariable("id") Long id) {
        Optional<Type> type = typeService.findById(id);
        if (!type.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(type.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Type> deleteType(@PathVariable("id") Long id) {
        Optional<Type> type = typeService.findById(id);
        if (!type.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        typeService.remove(id);
        return new ResponseEntity<>(type.get(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Iterable<TypeDTO>> getCountToursByCategories() {
        Iterable<TypeDTO> type = typeService.getTypeWithCountTour();
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

}
