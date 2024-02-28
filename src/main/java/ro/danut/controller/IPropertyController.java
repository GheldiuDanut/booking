package ro.danut.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.danut.entity.Property;

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
    @PostMapping
    default void createProperty(
            @Parameter(description = "Property to be saved")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Create", value = EXAMPLE)}
                    )) @RequestBody Property property) {
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})


    default void updatePropertyWithPut(
            @Parameter(description = "Property to be updated ")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Put", value = NEWPROPRIETY)}
                    )) @RequestBody Property property) {
    }

    String NEWPROPRIETY = "{\n" +
            "  \"Name\": \"\",\n" +
            "  \"Name\": \"\",\n" +
            "  \"Description\": \"\",\n" +
            "  \"PricePerNight\": \"\",\n" +
            "  \"Address\": \"\",\n" +
            "  \"TouristAttraction\": \"\",\n" +
            "  \"Facilities\": \"\"\n" +
            "}";


    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    default void updatePropertyWithPatch(
            @Parameter(description = "Property to be updated ")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Patch", value = NEWPROPRIETY)}
                    )) @RequestBody Property property) {
    }


}
