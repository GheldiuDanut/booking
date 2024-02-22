package ro.danut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;
import ro.danut.repository.PropertyRepository;
import ro.danut.repository.ReservationRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final PropertyRepository propertyRepository;

    public void save(Reservation reservation) {
        Optional<Property>optionalProperty = propertyRepository.findById(reservation.getProperty().getId());
        if (optionalProperty.isPresent()) {
            reservation.setProperty(optionalProperty.get());
            reservationRepository.save(reservation);
        }else {
            throw new RuntimeException("Property not found");
        }
    }

    public void updatePatch(Integer existingId, Map<String, Object> updates) {

    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getAllLocations() {
        return reservationRepository.findAll();
    }

//    public void deleteByReservationId(Integer propertyId,Integer reservationId) {
//        reservationRepository.deleteByReservationId(propertyId, reservationId);
//    }
}
