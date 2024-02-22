package ro.danut.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Column;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.danut.entity.Property;

public interface IPropertyController {
    String EXAMPLE = "{\n" +
            "  \"Name\": \"\",\n" +
            "  \"Description\": \"\",\n" +
            "  \"PricePerNight\": \"\",\n" +
            "  \"TouristAttraction\": \"\",\n" +
            "  \"Facilities\": \"\"\n" +
            "}";
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully saved"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    @PostMapping
    default void createProperty(
            @Parameter(description = "Property to be saved")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Create", value = EXAMPLE)}
                    )) @RequestBody Property property) {

    }
    @Operation(summary = "Update property with put.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})

    @PutMapping("/{id}")
    default void updatePropertyWithPut(
            @Parameter(description = "Property to be updated ")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Put", value = EXAMPLE)}
                    )) @RequestBody Property property) {
    }

    String PROPRIETY = "{\n" +
            "  \"Name\": \"\",\n" +
            "  \"Description\": \"\",\n" +
            "  \"PricePerNight\": \"\",\n" +
            "  \"Address\": \"\",\n" +
            "  \"TouristAttraction\": \"\",\n" +
            "  \"Facilities\": \"\"\n" +
            "}";
    @Operation(summary = "Update property with patch.")
    @PatchMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    default void updatePropertyWithPatch(
            @Parameter(description = "Property to be updated ")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Patch", value = PROPRIETY)}
                    )) @RequestBody Property property) {
    }



}
