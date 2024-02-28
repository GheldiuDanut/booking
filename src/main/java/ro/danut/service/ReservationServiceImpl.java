package ro.danut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;
import ro.danut.repository.PropertyRepository;
import ro.danut.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, PropertyRepository propertyRepository) {
        this.reservationRepository = reservationRepository;
        this.propertyRepository = propertyRepository;
    }

    //    Save a reservation in one period of time, if the reservation is not correct throw an error.
    public void save(Reservation reservation) {
        Optional<Property> optionalProperty = propertyRepository.findById(reservation.getProperty().getId());
        if (optionalProperty.isPresent()) {
            if (!reservationRepository.existsReservationInInterval(reservation.getProperty().getId(), reservation.getCheckInDate(), reservation.getCheckOutDate())) {
                reservation.setProperty(optionalProperty.get());
                reservationRepository.save(reservation);
            }
        } else {
            throw new RuntimeException("The period for reservation is not available.");
        }
    }

    //  Get all reservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    public void updatePatch(Integer existingId, Map<String, Object> updates) {
        var reservationOptional = reservationRepository.findById(existingId);
        if (reservationOptional.isEmpty()) {
            throw new RuntimeException("Reservation NOT Found");
        } else {
            Reservation reservation = reservationOptional.get();
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                switch (entry.getKey()) {
                    case "checkInDate":
                        reservation.setCheckInDate((LocalDate) entry.getValue());
                        break;
                    case "checkOutDate":
                        reservation.setCheckOutDate((LocalDate) entry.getValue());
                        break;
                    case "totalPrice":
                        reservation.setTotalPrice((Integer) entry.getValue());
                }
            }
        }
    }

    public void updatePut(Integer existingId, Reservation updatedReservation) {
        var reservationOptional = reservationRepository.findById(existingId);
        if (reservationOptional.isEmpty()) {
            throw new RuntimeException("Property NOT Found");
        }
        Reservation existingReservation = reservationOptional.get();
        existingReservation.setCheckInDate(updatedReservation.getCheckInDate());
        existingReservation.setCheckOutDate(updatedReservation.getCheckOutDate());
        existingReservation.setTotalPrice(updatedReservation.getTotalPrice());
        reservationRepository.save(existingReservation);
    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }


    public List<Reservation> getAllReservationForAProperty(String propertyName) {
        return getAllReservations().stream().
                filter(reservation -> reservation.getProperty().getName().equals(propertyName)).toList();
    }
}
