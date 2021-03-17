package cz.neidl.farmapp.repository;

import cz.neidl.farmapp.domain.Crop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropRepository extends CrudRepository<Crop, Long> {
    List<Crop> findAllByName(String name);
    List<Crop> findAll();
    Optional<Crop> findByName(String name);

    @Query("select c from Crop c where lower(c.name) like lower(concat('%',:searchTerm, '%'))")
    List<Crop> searchByName(@Param("searchTerm") String cropName);
}
