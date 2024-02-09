package ro.danut.controller.patch;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danut.service.BookingService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingPatchController {

    private final BookingService bookingService;


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBooking(
            @PathVariable("id") Integer existingId,
            @RequestBody Map<String, Object> updates) {
        bookingService.updatePatch(existingId, updates);
        return ResponseEntity.ok().build();
    }


}
