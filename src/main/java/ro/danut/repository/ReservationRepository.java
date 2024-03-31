package ro.danut.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.danut.dto.ReservationDto;
import ro.danut.entity.Reservation;

import java.time.LocalDate;
import java.util.List;



public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.property.id = :propertyId " +
            "AND ( r.checkInDate < :checkOut AND r.checkOutDate > :checkIn )")
    boolean existsReservationInInterval(@Param("propertyId") Integer propertyId,
                                        @Param("checkIn") LocalDate checkIn,
                                        @Param("checkOut") LocalDate checkOut);

    List<ReservationDto> findByPropertyName(String propertyName);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.property.id = :propertyId AND ((r.checkInDate BETWEEN :checkInDate AND :checkOutDate) OR (r.checkOutDate BETWEEN :checkInDate AND :checkOutDate)) AND r.id <> :reservationId")
    boolean overlappingReservation(@Param("propertyId") Integer propertyId,
                                   @Param("reservationId") Integer reservationId,
                                   @Param("checkInDate") LocalDate checkInDate,
                                   @Param("checkOutDate") LocalDate checkOutDate);

}
