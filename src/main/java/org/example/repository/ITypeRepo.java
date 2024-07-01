package org.example.repository;

import org.example.model.DTO.TypeDTO;
import org.example.model.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ITypeRepo extends CrudRepository<Type, Long> {
    @Query(nativeQuery = true, value =
            "select count(b.type_id) as dem, a.name,a.id from type a join tour b on a.id = b.type_id group by b.type_id;")
    Iterable<TypeDTO> getTypeWithCountTour();
}
