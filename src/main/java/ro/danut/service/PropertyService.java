package ro.danut.service;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ro.danut.entity.Property;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface PropertyService {
    List<Property> getAllPropertiesForATouristAttraction(String attraction);
    List<Property> getAllPropertiesForACertainPrice( int minPrice, int maxPrice);
    List<Property> getAllPropertiesForATouristAttractionAndForACertainPrice(
            String attraction,
            int minPrice,
            int maxPrice);
    List<Property> getAllPropertiesAvailableForATouristAttractionAndForACertainPrice(
            String attraction,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            int minPrice,
            int maxPrice);


    Optional<Property> getAPropertyByName(String name);

//    Optional<Property> getAPropertyByID(Integer id);
}
