package ro.danut.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.danut.dto.PropertyDto;
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
    public void createProperty(@RequestBody PropertyDto propertyDto) {
        propertyServiceImpl.save(propertyDto);
    }

    @Operation(summary = "Get a property by Id.")
    @GetMapping("/get-by-id/{id}")
    public Optional<PropertyDto> getById(@PathVariable Integer id) {
        return propertyServiceImpl.getAPropertyById(id);
    }

    @Operation(summary = "Get a property by Name.")
    @GetMapping("/get-by-name/{name}")
    public Optional<PropertyDto> getAPropertyByName(@PathVariable String name) {
        return propertyServiceImpl.getAPropertyByName(name);
    }

    @Operation(summary = "Get all properties.")
    @GetMapping("/all-properties")
    public List<PropertyDto> getAllProperties() {
        return propertyServiceImpl.getAllProperties();
    }

    @Operation(summary = "Get all proprieties by a tourist attraction.")
    @GetMapping("/get-all-proprieties/{attraction}")
    public List<PropertyDto> getAllPropertiesForATouristAttraction(@PathVariable String attraction) {
        return propertyServiceImpl.getAllPropertiesForATouristAttraction(attraction);
    }

    @Operation(summary = "Get all proprieties with a min price and max price.")
    @GetMapping("/get-all-proprieties/{minPrice}/{maxPrice}")
    public List<PropertyDto> getAllPropertiesForACertainPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return propertyServiceImpl.getAllPropertiesForACertainPrice(minPrice, maxPrice);
    }

    @Operation(summary = "Get all proprieties by a tourist attraction with a min price and max price.")
    @GetMapping("/get-all-proprieties/{attraction}/{minPrice}/{maxPrice}")
    public List<PropertyDto> getAllPropertiesForATouristAttractionAndForACertainPrice(
            @PathVariable String attraction,
            @PathVariable int minPrice,
            @PathVariable int maxPrice) {
        return propertyServiceImpl.
                getAllPropertiesForATouristAttractionAndForACertainPrice(attraction, minPrice, maxPrice);
    }

    @Operation(summary = "Get all proprieties available in a period of time for an tourist attraction with a min price and max price.")
    @GetMapping("/get-all-proprieties/{attraction}/{checkInDate}/{checkOutDate}/{minPrice}/{maxPrice}")
    public List<PropertyDto> getAllPropertiesAvailableForATouristAttractionAndForACertainPrice(
            @PathVariable LocalDate checkInDate,
            @PathVariable LocalDate checkOutDate,
            @PathVariable int minPrice,
            @PathVariable int maxPrice,
            @PathVariable String attraction){
        return propertyServiceImpl.getAllPropertiesAvailableForATouristAttractionAndForACertainPrice(checkInDate,checkOutDate,minPrice,maxPrice,attraction);

    }

    @Operation(summary = "Update property with patch.")
    @PatchMapping("/update-proprieties-patch/{id}")
    public void updatePropertyWithPatch(
            @RequestBody Map<String, Object> property,
            @PathVariable("id") Integer existingId) {
        propertyServiceImpl.updatePatch(existingId, property);
    }

    @Operation(summary = "Update property with put.")
    @PutMapping("/update-proprieties-put/{id}")
    public void updatePropertyWithPut(
            @RequestBody PropertyDto propertyDto ,
            @PathVariable("id") Integer existingId) {
        propertyServiceImpl.updatePut(existingId, propertyDto);
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
}