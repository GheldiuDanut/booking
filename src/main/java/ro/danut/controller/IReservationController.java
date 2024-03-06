package ro.danut.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Column;
import org.springframework.web.bind.annotation.*;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;

import java.time.LocalDate;

public interface IReservationController {

//    String EXAMPLE = "{\n" +
//            "  \"checkInDate\": \"\",\n" +
//            "  \"checkOutDate\": \"\",\n" +
//            "}";
    String EXAMPLE = "{\n" +
            "  \"checkInDate\": \"2024-03-00\",\n" +
            "  \"checkOutDate\": \"2024-03-00\"\n" +
            "}";


    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully saved"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    @PostMapping
    default void createReservation(
            @Parameter(description = "Reservation to be saved")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Create", value = EXAMPLE)}
                    )) @RequestBody Reservation reservation,@PathVariable Integer propertyId) {

    }
    @Operation(summary = "Update reservation with put.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    @PutMapping("/{id}")
    default void updateReservationWithPut(
            @Parameter(description = "Update reservation with put.")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Update", value = EXAMPLE)}
                    )) @RequestBody Reservation updatedReservation,@PathVariable Integer propertyId) {

    }
    @Operation(summary = "Update reservation with patch.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    @PatchMapping("/{id}")
    default void updateReservationWithPatch(
            @Parameter(description = "Update reservation with patch.")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Update", value = EXAMPLE)}
                    )) @RequestBody Reservation updatedReservation,@PathVariable Integer propertyId) {

    }
}
