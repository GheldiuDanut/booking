package ro.danut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.danut.entity.Property;
import ro.danut.repository.PropertyRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    public void deleteById(Integer id) {
        propertyRepository.deleteById(id);
    }

    public List<Property> getAllLocations() {
        return propertyRepository.findAll();
    }

    public void deleteByName(String name) {
        propertyRepository.deleteByName(name);
    }

    public void updatePatch(Integer existingId, Map<String, Object> locationMap) {
            var locationOptional = propertyRepository.findById(existingId);
            if (locationOptional.isEmpty()) {
                throw new RuntimeException("Location NOT Found");
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


    public void save(Property property) {
        propertyRepository.save(property);
    }
}
