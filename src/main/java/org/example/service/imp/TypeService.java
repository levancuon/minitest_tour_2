package org.example.service.imp;

import org.example.model.DTO.TypeDTO;
import org.example.model.Tour;
import org.example.model.Type;
import org.example.repository.ITypeRepo;
import org.example.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService implements ITypeService {
    @Autowired
    private ITypeRepo typeRepo;

    @Override
    public Iterable<Type> findAll() {
        return typeRepo.findAll();
    }

    @Override
    public void save(Type type) {
        typeRepo.save(type);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepo.findById(id);
    }

    @Override
    public void remove(Long id) {
        typeRepo.deleteById(id);
    }

    @Override
    public Iterable<TypeDTO> getTypeWithCountTour() {
        return typeRepo.getTypeWithCountTour();
    }

}
