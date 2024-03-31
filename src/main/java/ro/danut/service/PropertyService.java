package ro.danut.service;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ro.danut.dto.PropertyDto;
import ro.danut.entity.Property;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface PropertyService {
    List<PropertyDto> getAllPropertiesForATouristAttraction(String attraction);
    List<PropertyDto> getAllPropertiesForACertainPrice( int minPrice, int maxPrice);
    List<PropertyDto> getAllPropertiesForATouristAttractionAndForACertainPrice(
            String attraction,
            int minPrice,
            int maxPrice);

    Optional<PropertyDto> getAPropertyByName(String name);

    Optional<PropertyDto> getAPropertyById(Integer id);
}
