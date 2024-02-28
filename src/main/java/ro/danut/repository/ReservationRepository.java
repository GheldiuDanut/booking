package ro.danut.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import ro.danut.entity.Reservation;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.property.id = :propertyId " +
            "AND ((r.checkInDate <= :checkIn AND r.checkOutDate >= :checkIn) OR (r.checkInDate <= :checkOut AND r.checkOutDate >= :checkOut))")
    boolean existsReservationInInterval(@Param("propertyId") Integer propertyId,
                                        @Param("checkIn") LocalDate checkIn,
                                        @Param("checkOut") LocalDate checkOut);



}
