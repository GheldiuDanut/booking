package ro.danut.controller.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Column;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.danut.entity.Location;

public interface IBookingPostController {
    String EXAMPLE = """
            {
            \"name\": \"  \",
            \"description\": \"  \",
            \"price\": \"  \",
            \"address\": \"  \",
            \"touristAttraction\": \"  \",
            \"availability\": \"  \",
            \"facilities\": \"  \"
            }
                      """;

    @Operation(summary = "Save a new Location" +
            " in DB")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully saved"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    @PostMapping
    void createLocation(
            @Parameter(description = "Location to be saved")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "one", value = EXAMPLE)}
                    )) @RequestBody Location location);
}
