package ro.danut.controller;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danut.dto.ReservationDto;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;
import ro.danut.service.PropertyServiceImpl;
import ro.danut.service.ReservationServiceImpl;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/reservation")
public class ReservationController implements IReservationController{
    private final PropertyServiceImpl propertyServiceImpl;
    private final ReservationServiceImpl reservationServiceImpl;


    @Autowired
    public ReservationController(PropertyServiceImpl propertyServiceImpl, ReservationServiceImpl reservationServiceImpl) {
        this.propertyServiceImpl = propertyServiceImpl;
        this.reservationServiceImpl = reservationServiceImpl;
    }

    @PostMapping("/save-reservation/{propertyId}")
    @Operation(summary = "Save a new reservation.")
    public void createReservation(@RequestBody ReservationDto reservationDto, @PathVariable Integer propertyId) {
       reservationServiceImpl.save(propertyId, reservationDto);
    }

    @Operation(summary = "Get all reservation.")
    @GetMapping("/all-reservations")
    public List<ReservationDto> getAllReservations() {
        return reservationServiceImpl.getAllReservations();
    }


    @Operation(summary = "Get all reservation for a property.")
    @GetMapping("/all-reservations/{propertyName}")
    public List<ReservationDto> getAllReservationForAProperty(@PathVariable String propertyName) {
        return reservationServiceImpl.getAllReservationForAProperty(propertyName);

    }


    @PatchMapping("/update-reservation-patch/{id}")
    @Operation(summary = "Update reservation with patch.")
    public void updateReservationWithPatch(
            @RequestBody Map<String, Object> updatedReservation,
            @PathVariable("id") Integer propertyId)
           {
        reservationServiceImpl.updatePatch(propertyId, updatedReservation);
    }
    @Operation(summary = "Update reservation with put.")
    @PutMapping("/update-reservation-put/{propertyId}/{reservationId}")
    public void updateReservationWithPut(
            @RequestBody Reservation updatedReservation,
            @PathVariable("propertyId")Integer propertyId,
            @PathVariable("reservationId") Integer reservationId) {
        reservationServiceImpl.updatePut(propertyId,reservationId, updatedReservation);
    }


    @Operation(summary = "Delete a reservation by id.")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        reservationServiceImpl.deleteById(id);
    }

}
