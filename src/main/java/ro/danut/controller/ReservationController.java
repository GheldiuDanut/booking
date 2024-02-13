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
@RequestMapping("/bookings")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public void createBooking(@RequestBody Reservation reservation) {
        reservationService.save(reservation);
    }
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



    @GetMapping("/all-booking")
    public List<Reservation> getAllBookings() {
        return reservationService.getAllLocations();
    }

//    @GetMapping("/all-booking/{location}")
//    public List<Location> getAllBookingsForAnLocation(@PathVariable String attraction) {
//        return bookingService.getAllLocations().stream().
//                filter(location -> location.getTouristAttraction()
//                        .contains(attraction)).toList();
////        return bookingService.getAllLocationsByTouristAttraction();
//    }
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
