package ro.danut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.danut.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
///01-5///5-21
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.property.id = :propertyId " +
            "AND (r.checkInDate < :checkOut AND r.checkOutDate > :checkIn )")
    boolean existsReservationInInterval(@Param("propertyId") Integer propertyId,
                                        @Param("checkIn") LocalDate checkIn,
                                        @Param("checkOut") LocalDate checkOut);


    List<Reservation> findByPropertyName(String propertyName);




}
