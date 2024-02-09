package ro.danut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.danut.entity.Location;
import ro.danut.repository.ListingRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final ListingRepository listingRepository;



    public void deleteById(Integer id) {
        listingRepository.deleteById(id);
    }

    public List<Location> getAllLocations() {
        return listingRepository.findAll();
    }

    public void deleteByName(String name) {
         listingRepository.deleteByName(name);
    }

    public void updatePatch(Integer existingId, Map<String, Object> updates) {

    }
}
