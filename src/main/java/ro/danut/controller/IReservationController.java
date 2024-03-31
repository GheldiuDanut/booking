package ro.danut.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Column;
import org.springframework.web.bind.annotation.*;
import ro.danut.dto.ReservationDto;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;

import java.time.LocalDate;
import java.util.Map;

public interface IReservationController {


    String EXAMPLE = "{\n" +
            "  \"checkInDate\": \"2024-03-00\",\n" +
            "  \"checkOutDate\": \"2024-03-00\"\n" +
            "}";


    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully saved"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
    default void createReservation(
            @Parameter(description = "Reservation to be saved")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Create", value = EXAMPLE)}
                    )) @RequestBody ReservationDto reservationDto, @PathVariable Integer propertyId) {

    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
        @PutMapping
    default void updateReservationWithPut(
            @Parameter(description = "Update reservation with put.")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Update", value = EXAMPLE)}
                    )) @RequestBody Reservation updatedReservation,@PathVariable Integer propertyId,@PathVariable Integer reservationId) {
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input")})
        @PatchMapping
    default void updateReservationWithPatch(
            @Parameter(description = "Update reservation with patch.")
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Update", value = EXAMPLE)}
                    )) @RequestBody Map<String, Object> updatedReservation, @PathVariable("id") Integer reservationId) {
    }

    }

