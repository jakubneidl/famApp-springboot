package cz.neidl.farmapp.repository;

import cz.neidl.farmapp.domain.Harvest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HarvestRepository extends CrudRepository<Harvest,Long> {
    List<Harvest> findAll();
//    Optional<Harvest> findById();

}
