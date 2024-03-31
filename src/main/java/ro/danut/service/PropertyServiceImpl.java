package ro.danut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.danut.dto.PropertyDto;
import ro.danut.entity.Property;
import ro.danut.exception.FieldNotFoundException;
import ro.danut.exception.NotEnoughAttributeException;
import ro.danut.exception.PropertyNotFoundException;
import ro.danut.exception.SaveErrorException;
import ro.danut.mapper.PropertyMapper;
import ro.danut.repository.PropertyRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;

    //Save a property, if the property is not correct throw an error.
    public void save(PropertyDto propertyDto) {
        try {
            if (!propertyDto.getName().isEmpty() && !propertyDto.getAddress().isEmpty() &&
                    !propertyDto.getDescription().isEmpty() && !propertyDto.getTouristAttraction().isEmpty()
                    && !propertyDto.getFacilities().isEmpty() && !(propertyDto.getPricePerNight() < 0)) {
                Property property = PropertyMapper.INSTANCE.PropertyDtoToPropertyEntity(propertyDto);
                propertyRepository.save(property);
            } else {
                throw new NotEnoughAttributeException("Is necessary to put a name, address, " +
                        "description, tourist attraction, facilities and a price per night ");
            }
        } catch (Exception e) {
            throw new SaveErrorException("Failed to save property: " + e.getMessage());
        }
    }

    //Get all properties, else throw an error.
    public List<PropertyDto> getAllProperties() {
        try {
            return propertyRepository.findAll().stream()
                    .map(PropertyMapper.INSTANCE::PropertyEntityToPropertyDto)
                    .toList();
        } catch (Exception e) {
            throw new PropertyNotFoundException("Failed to get list of properties: " + e.getMessage());
        }
    }

    //Get a property by id.
    public Optional<PropertyDto> getAPropertyById(Integer id) {
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        return propertyOptional.map(PropertyMapper.INSTANCE::PropertyEntityToPropertyDto);
    }

    //Get a property by name.
    public Optional<PropertyDto> getAPropertyByName(String name) {
        Optional<Property> propertyOptional = propertyRepository.findByName(name);
        return propertyOptional.map(PropertyMapper.INSTANCE::PropertyEntityToPropertyDto);
    }

    //Get all properties for a tourist attraction.
    public List<PropertyDto> getAllPropertiesForATouristAttraction(String attraction) {
        List<Property> propertyOptional = propertyRepository.findByTouristAttraction(attraction);
        return propertyOptional.stream().map(PropertyMapper.INSTANCE::PropertyEntityToPropertyDto).toList();
    }

    //Get all properties for a certain price.
    public List<PropertyDto> getAllPropertiesForACertainPrice(int minPrice, int maxPrice) {
        List<Property> properties = propertyRepository.findByPricePerNightBetween(minPrice, maxPrice);
        return properties.stream().map(PropertyMapper.INSTANCE::PropertyEntityToPropertyDto).toList();
    }

    //Get all properties for a tourist attraction and for a certain price.
    public List<PropertyDto> getAllPropertiesForATouristAttractionAndForACertainPrice(
            String attraction,
            int minPrice,
            int maxPrice) {
        List<Property> properties = propertyRepository.findByPricePerNightBetweenAndTouristAttraction(minPrice, maxPrice, attraction);
        return properties.stream().map(PropertyMapper.INSTANCE::PropertyEntityToPropertyDto).toList();
    }

//    Get all properties available in a period of time for a tourist attraction and for a certain price.

    public List<PropertyDto> getAllPropertiesAvailableForATouristAttractionAndForACertainPrice(
            LocalDate checkInDate,
            LocalDate checkOutDate,
            int minPrice,
            int maxPrice,
            String attraction) {
        if (checkInDate.isAfter(checkOutDate) || checkInDate.isEqual(checkOutDate)) {
            throw new RuntimeException("Check in Date is bigger than or equal to check out date.");
        }
        List<Property> properties = propertyRepository.findAllAvailableProprieties(checkInDate, checkOutDate, minPrice, maxPrice, attraction);
        return properties.stream().map(PropertyMapper.INSTANCE::PropertyEntityToPropertyDto).collect(Collectors.toList());

    }

    //Update a part of property if is find, else throw error.
    public void updatePatch(Integer existingId, Map<String, Object> property) {
        var propertyOptional = propertyRepository.findById(existingId);
        if (propertyOptional.isEmpty()) {
            throw new PropertyNotFoundException("Property NOT Found");
        }
        Property newProperty = propertyOptional.get();
        for (Map.Entry<String, Object> entry : property.entrySet()) {
            switch (entry.getKey()) {
                case "Name", "name":
                    newProperty.setName((String) entry.getValue());
                    break;
                case "Address", "address":
                    newProperty.setAddress((String) entry.getValue());
                    break;
                case "Description", "description":
                    newProperty.setDescription((String) entry.getValue());
                    break;
                case "Price", "price":
                    newProperty.setPricePerNight((Integer) entry.getValue());
                    break;
                case "Facilities", "facilities":
                    newProperty.setFacilities((String) entry.getValue());
                    break;
                case "TouristAttraction", "touristAttraction":
                    newProperty.setTouristAttraction((String) entry.getValue());
                    break;
                default:
                    throw new FieldNotFoundException("Field  not recognized");
            }
        }
        propertyRepository.save(newProperty);
    }

    //Update property if is find, else throw an error.
    public void updatePut(Integer existingId, PropertyDto propertyDto) {
        var propertyOptional = propertyRepository.findById(existingId);
        if (propertyOptional.isEmpty()) {
            throw new PropertyNotFoundException("Property NOT Found");
        }
        Property existingProperty = propertyOptional.get();
        existingProperty.setName(propertyDto.getName());
        existingProperty.setAddress(propertyDto.getAddress());
        existingProperty.setDescription(propertyDto.getDescription());
        existingProperty.setPricePerNight(propertyDto.getPricePerNight());
        existingProperty.setFacilities(propertyDto.getFacilities());
        existingProperty.setTouristAttraction(propertyDto.getTouristAttraction());
        propertyRepository.save(existingProperty);
    }

    //Delete property by id, if the name is not correct throw an error
    public void deleteById(Integer id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            propertyRepository.deleteById(id);
        } else {
            throw new PropertyNotFoundException("The id for property is not valid");
        }
    }

    //Delete property by name, if the name is not correct throw an error
    public void deleteByName(String name) {
        Optional<Property> optionalProperty = propertyRepository.findByName(name);
        if (optionalProperty.isPresent()) {
            propertyRepository.deleteByName(name);
        } else {
            throw new FieldNotFoundException("The name for property is not valid");
        }
    }
}