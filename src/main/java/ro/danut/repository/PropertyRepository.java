package ro.danut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.danut.entity.Property;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    void deleteByName(String name);

    Optional<Property> findByName(String name);

    List<Property> findByTouristAttraction(String touristAttraction);

    List<Property> findByPricePerNightBetweenAndTouristAttraction(Integer minPrice, Integer maxPrice, String touristAttraction);

    List<Property> findByPricePerNightBetween(Integer minPrice, Integer maxPrice);

//    @Query("SELECT p FROM Property p LEFT JOIN p.reservations r " +
//            "WHERE p.touristAttraction = :touristAttraction " +
//            "AND p.pricePerNight >= :minPrice AND p.pricePerNight <= :maxPrice "+
//            "AND (r.checkInDate > :checkOut OR :checkIn > r.checkOutDate)" )


        @Query("SELECT p FROM Property p  " +
            "WHERE p.touristAttraction = :touristAttraction " +
            "AND p.pricePerNight >= :minPrice AND p.pricePerNight <= :maxPrice "+
            "AND p.id NOT IN (SELECT r.property.id FROM Reservation r WHERE (:checkIn > r.checkInDate AND :checkIn < r.checkOutDate) " +
                "OR (:checkOut > r.checkInDate AND :checkOut < r.checkOutDate) OR (:checkIn < r.checkInDate AND :checkOut > r.checkOutDate))" )
    List<Property> findAllAvailableProprieties(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("touristAttraction") String touristAttraction
    );



}
