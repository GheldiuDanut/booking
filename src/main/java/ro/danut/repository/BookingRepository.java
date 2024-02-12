package ro.danut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.danut.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
//    void deleteByName(String name);
}
