package ro.danut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.danut.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {


    void deleteByName(String name);
}
