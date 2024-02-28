package ro.danut.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danut.entity.Property;
import ro.danut.service.PropertyServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property")
public class PropertyController implements IPropertyController {
    private final PropertyServiceImpl propertyServiceImpl;
    @Operation(summary = "Save a new property.")
    @PostMapping("/create")
    public void createProperty(@RequestBody Property property) {
        propertyServiceImpl.save(property);
    }
    @Operation(summary = "Get a property by Id.")
    @GetMapping("/get-by-id/{id}")
    public Optional<Property> getById(@PathVariable Integer id) {
        return propertyServiceImpl.getAPropertyById(id);
    }
    @Operation(summary = "Get a property by Name.")
    @GetMapping("/get-by-name/{name}")
    public Optional<Property> getAPropertyByName(@PathVariable String name) {
        return propertyServiceImpl.getAPropertyByName(name);
    }
    @Operation(summary = "Get all property.")
    @GetMapping("/all-property")
    public List<Property> getAllProperties() {
        return propertyServiceImpl.getAllProperties();
    }
    @Operation(summary = "Get all proprieties by a tourist attraction.")
    @GetMapping("/get-all-proprieties/{attraction}")
    public List<Property> getAllPropertiesForATouristAttraction(@PathVariable String attraction) {
        return propertyServiceImpl.getAllPropertiesForATouristAttraction(attraction);
    }
    @Operation(summary = "Get all proprieties with a min price and max price.")
    @GetMapping("/get-all-proprieties/{minPrice}/{maxPrice}")
    public List<Property> getAllPropertiesForACertainPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return propertyServiceImpl.getAllPropertiesForACertainPrice(minPrice, maxPrice);
    }
    @Operation(summary = "Get all proprieties by a tourist attraction with a min price and max price.")
    @GetMapping("/get-all-proprieties/{attraction}/{minPrice}/{maxPrice}")
    public List<Property> getAllPropertiesForATouristAttractionAndForACertainPrice(
            @PathVariable String attraction,
            @PathVariable int minPrice,
            @PathVariable int maxPrice) {
        return propertyServiceImpl.
                getAllPropertiesForATouristAttractionAndForACertainPrice(attraction,minPrice,maxPrice);

    }


    @Operation(summary = "Get all proprieties available in a period of time for an tourist attraction with a min price and max price.")
    @GetMapping("/get-all-proprieties/{attraction}/{checkInDate}/{checkOutDate}/{minPrice}/{maxPrice}")
    public List<Property> getAllPropertiesAvailableForATouristAttractionAndForACertainPrice(
            @PathVariable String attraction,
            @PathVariable LocalDate checkInDate,
            @PathVariable LocalDate checkOutDate,
            @PathVariable int minPrice,
            @PathVariable int maxPrice){
        return propertyServiceImpl.getAllPropertiesAvailableForATouristAttractionAndForACertainPrice(attraction,checkInDate,checkOutDate,
                minPrice,maxPrice);
    }

    @Operation(summary = "Update property with patch.")
    @PatchMapping("/update-proprieties-patch/{id}")
    public ResponseEntity<Void> updatePropertyWithPatch(
            @PathVariable("id") Integer existingId,
            @RequestBody Map<String, Object> updates) {
        propertyServiceImpl.updatePatch(existingId, updates);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update property with put.")
    @PutMapping("/update-proprieties-put/{id}")
    public ResponseEntity<Void> updatePropertyWithPut(
            @PathVariable("id") Integer existingId,
            @RequestBody Property updatedProperty) {
        propertyServiceImpl.updatePut(existingId, updatedProperty);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Delete the propriety by an id.")
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(@PathVariable Integer id) {
        propertyServiceImpl.deleteById(id);
    }

    @Operation(summary = "Delete the propriety by a name.")
    @DeleteMapping("/delete-by-name/{name}")
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





}
