package org.example.repository;

import org.example.model.Tour;
import org.example.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ITourRepo extends PagingAndSortingRepository<Tour, Long> {
    Page<Tour> findAll(Pageable pageable);

    Iterable<Tour> findAllByType(Type type);

    Page<Tour> findAllByCodeContaining(Pageable pageable, String code);

}
