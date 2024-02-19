package ro.danut.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danut.entity.Reservation;
import ro.danut.service.ReservationService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public void createReservation(@RequestBody Reservation reservation) {
        reservationService.save(reservation);
    }

    //I must check if it works
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBooking(
            @PathVariable("id") Integer existingId,
            @RequestBody Map<String, Object> updates) {
        reservationService.updatePatch(existingId, updates);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        reservationService.deleteById(id);
    }
//    @DeleteMapping("/{propertyId}/{reservationId}")
//    public void deleteByReservationId(@PathVariable Integer propertyId, @PathVariable Integer reservationId) {
//        reservationService.deleteByReservationId(propertyId, reservationId);
//    }

    @GetMapping("/all-reservations")
    public List<Reservation> getAllBookings() {
        return reservationService.getAllLocations();
    }

    @GetMapping("/all-reservations/{location}")
    public List<Reservation> getAllReservationForAnProperty(@PathVariable String property) {
        return reservationService.getAllLocations().stream().
                filter(location -> location.getProperty().equals(location)).toList();

    }
//
////    @Column(nullable = false)
////    private LocalDate checkInDate;
////
////    @Column(nullable = false)
////    private LocalDate checkOutDate;
////
////    @Column(nullable = false)
////    private double totalPrice;
////
////    @ManyToOne(cascade = CascadeType.ALL)
////    private Location location;
//    @GetMapping("/location/{minPrice}/{maxPrice}")
//    public List<Location> getAllBookingsForAnCertainPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
//        return bookingService.getAllLocations().stream()
//                .filter(location -> location.getPrice() >= minPrice && location.getPrice() <= maxPrice)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/bookings/{attraction}/{minPrice}/{maxPrice}")
//    public List<Location> getAllBookingsForAnTouristAttractionAndForAnCertainPrice(
//            @PathVariable String attraction,
//            @PathVariable int minPrice,
//            @PathVariable int maxPrice) {
//        return bookingService.getAllLocations().stream()
//                .filter(location -> location.getTouristAttraction()
//                        .contains(attraction) &&
//                        location.getPrice() >= minPrice && location.getPrice() <= maxPrice)
//                .collect(Collectors.toList());
//    }
//    @GetMapping("/location/{attraction}/{minPrice}/{maxPrice}")
//    public List<Location> getAllBookingsForAnLocationAndForAnTouristAttractionAndForAnCertainPrice(
//            @PathVariable String attraction,
//            @PathVariable int minPrice,
//            @PathVariable int maxPrice) {
//        return bookingService.getAllLocations().stream()
//                .filter(location -> location.getTouristAttraction()
//                        .contains(attraction) &&
//                        location.getPrice() >= minPrice && location.getPrice() <= maxPrice)
//                .collect(Collectors.toList());
//    }


}
