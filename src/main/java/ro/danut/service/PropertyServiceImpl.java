package ro.danut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.danut.entity.Property;
import ro.danut.repository.PropertyRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;

    //Save a property, if the property is not correct throw an error.
    public void save(Property property) {
        try {
            if (!property.getName().isEmpty() && !property.getAddress().isEmpty() &&
                    !property.getDescription().isEmpty() && !property.getTouristAttraction().isEmpty()
                    && !property.getFacilities().isEmpty() && !(property.getPricePerNight() < 0)) {
                propertyRepository.save(property);
            } else {
                throw new RuntimeException("Is necessary to put a name, address, " +
                        "description, tourist attraction, facilities and a price per night ");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save property: " + e.getMessage());
        }
    }

    //Get all properties, else throw an error.
    public List<Property> getAllProperties() {
        try {
            return propertyRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get list of properties: " + e.getMessage());
        }
    }

    //Get a property by id.
    public Optional<Property> getAPropertyById(Integer id) {
        return propertyRepository.findById(id);
    }

    //Get a property by name.
    public Optional<Property> getAPropertyByName(String name) {
        return propertyRepository.findByName(name);
    }

    //Get all properties for a tourist attraction.
    public List<Property> getAllPropertiesForATouristAttraction(String attraction) {
        return propertyRepository.findByTouristAttraction(attraction);
    }

    //Get all properties for a certain price.
    public List<Property> getAllPropertiesForACertainPrice(int minPrice, int maxPrice) {
        return propertyRepository.findByPricePerNightBetween(minPrice, maxPrice);
    }

    //Get all properties for a tourist attraction and for a certain price.
    public List<Property> getAllPropertiesForATouristAttractionAndForACertainPrice(
            String attraction,
            int minPrice,
            int maxPrice) {
        return propertyRepository.findByPricePerNightBetweenAndTouristAttraction(minPrice, maxPrice, attraction);
    }



//    Get all properties available in a period of time for a tourist attraction and for a certain price.

    public List<Property> getAllPropertiesAvailableForATouristAttractionAndForACertainPrice(
//            LocalDate checkIn,
//            LocalDate checkOut,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            int minPrice,
            int maxPrice,
            String attraction) {
        return propertyRepository.findByCheckInBetweenAndCheckOutBetweenAndPriceBetweenAndTouristAttraction(checkInDate, checkOutDate,  minPrice, maxPrice, attraction);
    }





//        getAllPropertiesAvailableForATouristAttractionAndForACertainPrice(attraction, checkInDate, checkOutDate, minPrice, maxPrice).stream()
//                .filter(property -> property.getTouristAttraction()
//                        .contains(attraction) && isAvailableInIntervalOfTime(checkInDate, checkOutDate,property) &&
//                        property.getPricePerNight() >= minPrice && property.getPricePerNight() <= maxPrice)
//                .collect(Collectors.toList());
//    }
//    private static boolean isAvailableInIntervalOfTime(LocalDate checkIn, LocalDate checkOut, Property property) {
//        if (checkOut.isBefore(checkIn)) {
//            throw new RuntimeException("CheckIn must be before CheckOut");
//        }
//        long count = property.getReservations().stream()
//                .filter(r -> checkOut.isAfter(r.getCheckInDate()) && checkIn.isBefore(r.getCheckOutDate()))
//                .peek(System.out::println)
//                .count();
//        return count == 0;
//    }

    //Update a part of property if is find, else throw error.
    public void updatePatch(Integer existingId, Map<String, Object> property) {
        var propertyOptional = propertyRepository.findById(existingId);
        if (propertyOptional.isEmpty()) {
            throw new RuntimeException("Property NOT Found");
        }
        Property newProperty = propertyOptional.get();
        for (Map.Entry<String, Object> entry : property.entrySet()) {
            switch (entry.getKey()) {
                case "Name","name":
                    newProperty.setName((String) entry.getValue());
                    break;
                case "Address","address":
                    newProperty.setAddress((String) entry.getValue());
                    break;
                case "Description","description":
                    newProperty.setDescription((String) entry.getValue());
                    break;
                case "Price","price":
                    newProperty.setPricePerNight((Integer) entry.getValue());
                    break;
                case "Facilities","facilities":
                    newProperty.setFacilities((String) entry.getValue());
                    break;
                case "TouristAttraction","touristAttraction":
                    newProperty.setTouristAttraction((String) entry.getValue());
                    break;
                default:
                    throw new RuntimeException("Field  not recognized");
            }
        }
        propertyRepository.save(newProperty);
    }

    //Update property if is find, else throw an error.
    public void updatePut(Integer existingId, Property property) {
        var propertyOptional = propertyRepository.findById(existingId);
        if (propertyOptional.isEmpty()) {
            throw new RuntimeException("Property NOT Found");
        }
        Property existingProperty = propertyOptional.get();
        existingProperty.setName(property.getName());
        existingProperty.setAddress(property.getAddress());
        existingProperty.setDescription(property.getDescription());
        existingProperty.setPricePerNight(property.getPricePerNight());
        existingProperty.setFacilities(property.getFacilities());
        existingProperty.setTouristAttraction(property.getTouristAttraction());
        propertyRepository.save(existingProperty);
    }

    //Delete property by id, if the name is not correct throw an error
    public void deleteById(Integer id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            propertyRepository.deleteById(id);
        } else {
            throw new RuntimeException("The id for property is not valid");
        }
    }

    //Delete property by name, if the name is not correct throw an error
    public void deleteByName(String name) {
        Optional<Property> optionalProperty = propertyRepository.findByName(name);
        if (optionalProperty.isPresent()) {
            propertyRepository.deleteByName(name);
        } else {
            throw new RuntimeException("The name for property is not valid");
        }
    }


}
