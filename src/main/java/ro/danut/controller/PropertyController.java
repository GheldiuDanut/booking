package ro.danut.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danut.entity.Property;
import ro.danut.service.PropertyService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    public void createProperty(@RequestBody Property property) {
        propertyService.save(property);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProperty(
            @PathVariable("id") Integer existingId,
            @RequestBody Map<String, Object> updates) {
        propertyService.updatePatch(existingId, updates);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePropertyWithPut(
            @PathVariable("id") Integer existingId,
            @RequestBody Property updatedProperty) {
        propertyService.updatePut(existingId, updatedProperty);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        propertyService.deleteById(id);
    }

    @DeleteMapping("/delete/{name}")
    @Transactional
    public void deleteByName(@PathVariable String name) {
        propertyService.deleteByName(name);
    }

    @GetMapping("/all-property")
    public List<Property> getAllProperties() {
        return propertyService.getAllLocations();
    }

    @GetMapping("/property/{attraction}")
    public List<Property> getAllPropertiesForAnTouristAttraction(@PathVariable String attraction) {
        return propertyService.getAllLocations().stream().
                filter(location -> location.getTouristAttraction()
                        .contains(attraction)).toList();
    }

    // Must put all proprieties that are available
//    @GetMapping("/property/{attraction}")
//    public List<Property> getAllPropertiesAvailableForAnTouristAttraction(@PathVariable String attraction) {
//        return propertyService.getAllLocations().stream().
//                filter(location -> location.getTouristAttraction()
//                        .contains(attraction)).toList();
//    }


    @GetMapping("/property/{minPrice}/{maxPrice}")
    public List<Property> getAllPropertiesForAnCertainPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return propertyService.getAllLocations().stream()
                .filter(location -> location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
                .collect(Collectors.toList());
    }
    //Must put all proprieties that are available with that price
//    @GetMapping("/property/{minPrice}/{maxPrice}")
//    public List<Property> getAllPropertiesAvailableForAnCertainPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
//        return propertyService.getAllLocations().stream()
//                .filter(location -> location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
//                .collect(Collectors.toList());
//    }


    @GetMapping("/property/{attraction}{minPrice}/{maxPrice}")
    public List<Property> getAllPropertiesForAnTouristAttractionAndForAnCertainPrice(
            @PathVariable String attraction,
            @PathVariable int minPrice,
            @PathVariable int maxPrice) {
        return propertyService.getAllLocations().stream()
                .filter(location -> location.getTouristAttraction()
                        .contains(attraction) &&
                        location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
                .collect(Collectors.toList());
    }


    //Must put all proprieties that are available at that attraction and with that price
//    @GetMapping("/property/{attraction}/property/{minPrice}/{maxPrice}")
//    public List<Property> getAllPropertiesAvailableForAnTouristAttractionAndForAnCertainPrice(
//            @PathVariable String attraction,
//            @PathVariable int minPrice,
//            @PathVariable int maxPrice) {
//        return propertyService.getAllLocations().stream()
//                .filter(location -> location.getTouristAttraction()
//                        .contains(attraction) &&
//                        location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
//                .collect(Collectors.toList());
//    }
}
