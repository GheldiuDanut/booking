package ro.danut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.danut.entity.Booking;
import ro.danut.entity.Location;
import ro.danut.repository.BookingRepository;
import ro.danut.repository.LocationRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;


    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public void updatePatch(Integer existingId, Map<String, Object> updates) {

    }

    public void deleteById(Integer id) {
        bookingRepository.deleteById(id);
    }



    public List<Booking> getAllLocations() {
        return bookingRepository.findAll();
    }
}
