package ro.danut.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import ro.danut.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {



//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Reservation r WHERE r.property.id = :propertyId AND r.id = :id ")
//    void deleteByReservationId(@Param("propertyId") Integer propertyId,@Param("id") Integer id);



}
