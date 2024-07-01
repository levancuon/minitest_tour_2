package org.example.service.imp;

import org.example.model.Tour;
import org.example.model.Type;
import org.example.repository.ITourRepo;
import org.example.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourService implements ITourService {
    @Autowired
    private ITourRepo iTourRepo;

    @Override
    public Iterable<Tour> findAll() {
        return iTourRepo.findAll();
    }

    @Override
    public void save(Tour tour) {
        iTourRepo.save(tour);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return iTourRepo.findById(id);
    }

    @Override
    public void remove(Long id) {
        iTourRepo.deleteById(id);
    }


    @Override
    public Iterable<Tour> findAllByType(Type type) {
        return iTourRepo.findAllByType(type);
    }

    @Override
    public Page<Tour> findAll(Pageable pageable) {
        return iTourRepo.findAll(pageable);
    }

    @Override
    public Page<Tour> findByCodeEndingWith(Pageable pageable, String code) {
        return iTourRepo.findAllByCodeContaining(pageable, code);
    }

    @Override
    public Page<Tour> findAllAndSort(String sort) {
        //        sort = "code asc"
        String[] sortt = sort.split(" ");
        Sort sortS = Sort.by(sortt[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortt[0]);
        Pageable pageable = PageRequest.of(0, 1000, sortS);
        return iTourRepo.findAll(pageable);

    }

}
