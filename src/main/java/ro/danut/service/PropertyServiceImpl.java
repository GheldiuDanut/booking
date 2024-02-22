package ro.danut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.danut.entity.Property;
import ro.danut.repository.PropertyRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;

    //Save a property, if the property is not correct throw an error.
    public void save(Property property) {
        try {
            propertyRepository.save(property);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save property: " + e.getMessage());
        }
    }
    //Get all properties, else throw an error.
    public List<Property> getAllProperties() {
        try {
            return propertyRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Failed to get list of properties: " + e.getMessage());
        }
    }
    //Update a part of property if is find, else throw error.
    public void updatePatch(Integer existingId, Map<String, Object> locationMap) {
        var locationOptional = propertyRepository.findById(existingId);
        if (locationOptional.isEmpty()) {
            throw new RuntimeException("Property NOT Found");
        }
        Property property = locationOptional.get();
        for (Map.Entry<String, Object> entry : locationMap.entrySet()) {
            switch (entry.getKey()) {
                case "Name":
                    property.setName((String) entry.getValue());
                    break;
                case "Address":
                    property.setAddress((String) entry.getValue());
                    break;
                case "Description":
                    property.setDescription((String) entry.getValue());
                    break;
                case "Price":
                    property.setPricePerNight((Integer) entry.getValue());
                    break;
                case "Facilities":
                    property.setFacilities((String) entry.getValue());
                    break;
                case "TouristAttraction":
                    property.setTouristAttraction((String) entry.getValue());
                    break;
                default:
                    throw new RuntimeException("Field  not recognized");
            }
        }
        propertyRepository.save(property);
    }
    //Update property if is find, else throw an error.
    public void updatePut(Integer existingId, Property newProperty) {
        var propertyOptional = propertyRepository.findById(existingId);
        if (propertyOptional.isEmpty()) {
            throw new RuntimeException("Property NOT Found");
        }
        Property existingProperty = propertyOptional.get();
        existingProperty.setName(newProperty.getName());
        existingProperty.setAddress(newProperty.getAddress());
        existingProperty.setDescription(newProperty.getDescription());
        existingProperty.setPricePerNight(newProperty.getPricePerNight());
        existingProperty.setFacilities(newProperty.getFacilities());
        existingProperty.setTouristAttraction(newProperty.getTouristAttraction());
        propertyRepository.save(existingProperty);
    }
    //Delete property by id, if the name is not correct throw an error
    public void deleteById(Integer id) {
        Optional<Property>optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            propertyRepository.deleteById(id);
        }else {
            throw  new RuntimeException("The id for property is not valid");
        }
    }
    //Delete property by name, if the name is not correct throw an error
    public void deleteByName(String name) {
        Optional<Property>optionalProperty = propertyRepository.findByName(name);
        if (optionalProperty.isPresent()) {
            propertyRepository.deleteByName(name);
        }else {
            throw new RuntimeException("The name for property is not valid");
        }
    }
}
