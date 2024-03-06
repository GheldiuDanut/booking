package ro.danut.controller;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;
import ro.danut.service.PropertyServiceImpl;
import ro.danut.service.ReservationServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

@RequestMapping("/reservations")
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
    public void createReservation(@RequestBody Reservation reservation,@PathVariable Integer propertyId) {
       reservationServiceImpl.save(propertyId,reservation);
    }

    @Operation(summary = "Get all reservation.")
    @GetMapping("/all-reservations")
    public List<Reservation> getAllReservations() {
        return reservationServiceImpl.getAllReservations();
    }
    // Get all reservation for a property



    @Operation(summary = "Get all reservation for a property.")
    @GetMapping("/all-reservations/{propertyName}")
    public List<Reservation> getAllReservationForAProperty(@PathVariable String propertyName) {
        return reservationServiceImpl.getAllReservationForAProperty(propertyName);

    }
//    @Operation(summary = "Get all reservation for a property.")
//    @GetMapping("/all-reservations/{property}")
//    public List<Reservation> getAllPropertiesAvailableForReservationForATouristAttractionAndForACertainPrice(
//            @PathVariable String attraction,
//            @PathVariable LocalDate checkInDate,
//            @PathVariable LocalDate checkOutDate,
//            @PathVariable int minPrice,
//            @PathVariable int maxPrice
//    ) {
//        return reservationServiceImpl.getAllPropertiesAvailableForReservationForATouristAttractionAndForACertainPrice(attraction,checkInDate,checkOutDate,minPrice,maxPrice);
//
//    }


    //I must check if it works.
    public ResponseEntity<Void> updateReservationWithPatch(
            @PathVariable("id") Integer propertyId,
            @RequestBody Map<String, Object> updatedReservation) {
        reservationServiceImpl.updatePatch(propertyId, updatedReservation);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<Void> updateReservationWithPut(
            @PathVariable("id") Integer propertyId,
            @RequestBody Reservation updatedReservation) {
        reservationServiceImpl.updatePut(propertyId, updatedReservation);
        return ResponseEntity.ok().build();
    }
    //Delete a reservation by id.
    @Operation(summary = "Delete a reservation by id.")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        reservationServiceImpl.deleteById(id);
    }

}
