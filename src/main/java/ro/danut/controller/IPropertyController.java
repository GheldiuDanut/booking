package ro.danut.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ro.danut.dto.PropertyDto;
import ro.danut.entity.Property;

import java.util.Map;

public interface IPropertyController {
    String EXAMPLE = "{\n" +
            "  \"name\": \"\",\n" +
            "  \"description\": \"\",\n" +
            "  \"pricePerNight\":  0,\n" +
            "  \"address\": \"\",\n" +
            "  \"touristAttraction\": \"\",\n" +
            "  \"facilities\": \"\"\n" +
            "}";

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully saved"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    default void createProperty(
            @Parameter(description = "Property to be saved")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Create", value = EXAMPLE)}
                    )) @RequestBody PropertyDto propertyDto) {
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    default void updatePropertyWithPatch(
            @Parameter(description = "Property to be partially updated")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Patch", value = EXAMPLE)}
                    )) @RequestBody Map<String, Object> propertyDto, @PathVariable("id") Integer existingId) {
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    default void updatePropertyWithPut(
            @Parameter(description = "Property to be updated ")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Put", value = EXAMPLE)}
                    )) @RequestBody PropertyDto propertyDto, @PathVariable("id") Integer existingId) {
    }
}
