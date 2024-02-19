package ro.danut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.danut.entity.Property;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

    void deleteByName(String name);

}
