package ro.danut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.danut.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
//    void deleteByName(String name);
}
