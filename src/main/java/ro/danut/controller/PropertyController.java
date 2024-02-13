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
@RequestMapping("/locations")
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    public void createLocation(@RequestBody Property property) {
        propertyService.save(property);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateLocation(
            @PathVariable("id") Integer existingId,
            @RequestBody Map<String, Object> updates) {
        propertyService.updatePatch(existingId, updates);
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

    @GetMapping("/all-locations")
    public List<Property> getAllLocations() {
        return propertyService.getAllLocations();
    }

    @GetMapping("/locations/{attraction}")
    public List<Property> getAllLocationsForAnTouristAttraction(@PathVariable String attraction) {
        return propertyService.getAllLocations().stream().
                filter(location -> location.getTouristAttraction()
                        .contains(attraction)).toList();
    }

    @GetMapping("/location/{minPrice}/{maxPrice}")
    public List<Property> getAllLocationForAnCertainPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return propertyService.getAllLocations().stream()
                .filter(location -> location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
                .collect(Collectors.toList());
    }

    @GetMapping("/location/{attraction}/{minPrice}/{maxPrice}")
    public List<Property> getAllLocationsForAnTouristAttractionAndForAnCertainPrice(
            @PathVariable String attraction,
            @PathVariable int minPrice,
            @PathVariable int maxPrice) {
        return propertyService.getAllLocations().stream()
                .filter(location -> location.getTouristAttraction()
                        .contains(attraction) &&
                        location.getPricePerNight() >= minPrice && location.getPricePerNight() <= maxPrice)
                .collect(Collectors.toList());
    }
}
