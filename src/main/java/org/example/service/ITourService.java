package org.example.service;

import org.example.model.Tour;
import org.example.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ITourService extends IGenericService<Tour> {
    Iterable<Tour> findAllByType(Type type);

    Page<Tour> findAll(Pageable pageable);


    Page<Tour> findByCodeEndingWith(Pageable pageable,String code);

    Page<Tour> findAllAndSort(String sort);
}
