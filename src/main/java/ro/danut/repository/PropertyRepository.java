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


    @Query("SELECT p FROM Property p JOIN p.reservations r WHERE r.checkInDate < :checkOut AND r.checkOutDate > :checkIn AND (:minPrice IS NULL OR p.pricePerNight >= :minPrice) AND (:maxPrice IS NULL OR p.pricePerNight <= :maxPrice) AND (:touristAttraction IS NULL OR p.touristAttraction = :touristAttraction)")
    List<Property> findByCheckInBetweenAndCheckOutBetweenAndPriceBetweenAndTouristAttraction(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("touristAttraction") String touristAttraction
    );


//    List<Property>findByCheckInBetweenAndCheckOutBetweenAndPriceBetweenAndTouristAttraction(LocalDate checkIn, LocalDate checkOut,LocalDate checkInDate,LocalDate checkOutDate,Integer minPrice, Integer maxPrice, String touristAttraction);


}
