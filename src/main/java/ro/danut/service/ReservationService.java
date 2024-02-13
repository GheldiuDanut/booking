package ro.danut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.danut.entity.Reservation;
import ro.danut.repository.ReservationRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;


    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updatePatch(Integer existingId, Map<String, Object> updates) {

    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }



    public List<Reservation> getAllLocations() {
        return reservationRepository.findAll();
    }
}
