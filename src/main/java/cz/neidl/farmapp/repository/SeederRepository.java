package cz.neidl.farmapp.repository;

import cz.neidl.farmapp.domain.Seeder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeederRepository extends CrudRepository<Seeder, Long> {
    List<Seeder> findAll();
    Optional<Seeder> findByName(String name);
}
