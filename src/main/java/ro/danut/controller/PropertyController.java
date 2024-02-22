package ro.danut.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danut.entity.Property;
import ro.danut.service.PropertyServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/properties")
public class PropertyController implements IPropertyController {
    private final PropertyServiceImpl propertyServiceImpl;
    @Operation(summary = "Save a new property in DB.")
    @PostMapping
    public void createProperty(@RequestBody Property property) {
        propertyServiceImpl.save(property);
    }
    @Operation(summary = "Get all property.")
    @GetMapping("/all-property")
    public List<Property> getAllProperties() {
        return propertyServiceImpl.getAllProperties();
    }

    @Operation(summary = "Get all proprieties by a tourist attraction.")
    @GetMapping("/property/{attraction}")
    public List<Property> getAllPropertiesForAnTouristAttraction(@PathVariable String attraction) {
        return propertyServiceImpl.getAllProperties().stream().
                filter(location -> location.getTouristAttraction()
                        .contains(attraction)).toList();
    }
    @Operation(summary = "Get all proprieties by a tourist attraction with a min price and max price")
    @GetMapping("/property/{attraction}{minPrice}/{maxPrice}")
    public List<Property> getAllPropertiesForAnTouristAttractionAndForAnCertainPrice(
            @PathVariable String attraction,
            @PathVariable int minPrice,
            @PathVariable int maxPrice) {
        return propertyServiceImpl.getAllProperties().stream()
                .filter(location -> location.getTouristAttraction()
                        .contains(attraction) &&
                        location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
                .collect(Collectors.toList());
    }
    @Operation(summary = "Get all proprieties with a min price and max price")
    @GetMapping("/property/{minPrice}/{maxPrice}")
    public List<Property> getAllPropertiesForAnCertainPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return propertyServiceImpl.getAllProperties().stream()
                .filter(location -> location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
                .collect(Collectors.toList());
    }
    public ResponseEntity<Void> updatePropertyWithPatch(
            @PathVariable("id") Integer existingId,
            @RequestBody Map<String, Object> updates) {
        propertyServiceImpl.updatePatch(existingId, updates);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<Void> updatePropertyWithPut(
            @PathVariable("id") Integer existingId,
            @RequestBody Property updatedProperty) {
        propertyServiceImpl.updatePut(existingId, updatedProperty);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Delete the propriety by an id")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        propertyServiceImpl.deleteById(id);
    }
    @Operation(summary = "Delete the propriety by a name")
    @DeleteMapping("/delete/{name}")
    @Transactional
    public void deleteByName(@PathVariable String name) {
        propertyServiceImpl.deleteByName(name);
    }



    // Must put all proprieties that are available
//    @GetMapping("/property/{attraction}")
//    public List<Property> getAllPropertiesAvailableForAnTouristAttraction(@PathVariable String attraction) {
//        return propertyService.getAllLocations().stream().
//                filter(location -> location.getTouristAttraction()
//                        .contains(attraction)).toList();
//    }



    //Must put all proprieties that are available with that price
//    @GetMapping("/property/{minPrice}/{maxPrice}")
//    public List<Property> getAllPropertiesAvailableForAnCertainPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
//        return propertyService.getAllLocations().stream()
//                .filter(location -> location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
//                .collect(Collectors.toList());
//    }





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
