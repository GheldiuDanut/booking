package ro.danut.controller;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;
import ro.danut.service.ReservationServiceImpl;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/reservations")
public class ReservationController implements IReservationController{


    private final ReservationServiceImpl reservationServiceImpl;


    @Autowired
    public ReservationController(ReservationServiceImpl reservationServiceImpl) {
        this.reservationServiceImpl = reservationServiceImpl;
    }

    @Operation(summary = "Save a new reservation.")
    @PostMapping
    public void createReservation(@RequestBody Reservation reservation) {
        reservationServiceImpl.save(reservation);
    }

    @Operation(summary = "Get all reservation.")
    @GetMapping("/all-reservations")
    public List<Reservation> getAllReservations() {
        return reservationServiceImpl.getAllReservations();
    }
    // Get all reservation for a property


    //must change t
    @Operation(summary = "Get all reservation for a property.")
    @GetMapping("/all-reservations/{property}")
    public List<Reservation> getAllReservationForAProperty(@PathVariable String propertyName) {
        return reservationServiceImpl.getAllReservationForAProperty(propertyName);

    }
















    //I must check if it works.
    public ResponseEntity<Void> updateReservationWithPatch(
            @PathVariable("id") Integer existingId,
            @RequestBody Map<String, Object> updates) {
        reservationServiceImpl.updatePatch(existingId, updates);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<Void> updateReservationWithPut(
            @PathVariable("id") Integer existingId,
            @RequestBody Reservation updatedReservation) {
        reservationServiceImpl.updatePut(existingId, updatedReservation);
        return ResponseEntity.ok().build();
    }
    //Delete a reservation by id.
    @Operation(summary = "Delete a reservation by id.")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        reservationServiceImpl.deleteById(id);
    }

}
