package ro.danut.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.danut.dto.PropertyDto;
import ro.danut.dto.ReservationDto;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;
import ro.danut.exception.FieldNotFoundException;
import ro.danut.exception.PeriodIsNotAvailableException;
import ro.danut.exception.PropertyNotFoundException;
import ro.danut.exception.ReservationNotFoundException;
import ro.danut.mapper.PropertyMapper;
import ro.danut.mapper.ReservationMapper;
import ro.danut.repository.PropertyRepository;
import ro.danut.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    // Save a reservation in one period of time, if the reservation is not correct throw an error.
    public void save(Integer propertyId, ReservationDto reservationDto) {
        if (reservationDto.getCheckInDate().isAfter(reservationDto.getCheckOutDate())) {
            throw new PeriodIsNotAvailableException("Check in Date is bigger than check out date");
        }
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isPresent()) {
            if (!reservationRepository.existsReservationInInterval(optionalProperty.get().getId(), reservationDto.getCheckInDate(), reservationDto.getCheckOutDate())) {
                reservationDto.setProperty(optionalProperty.get());
                long daysBetween = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
                reservationDto.setTotalPrice(optionalProperty.get().getPricePerNight() * daysBetween);
                Reservation reservation = ReservationMapper.INSTANCE.ReservationDtoToReservationEntity(reservationDto);
                reservationRepository.save(reservation);
            } else {
                throw new PeriodIsNotAvailableException("The period for reservation is not available.");
            }
        } else {
            throw new PropertyNotFoundException("The property id is not valid.");
        }
    }

    // Get all reservations.
    public List<ReservationDto> getAllReservations() {
        try {
            return reservationRepository.findAll().stream()
                    .map(ReservationMapper.INSTANCE::ReservationEntityToReservationDto)
                    .toList();
        } catch (Exception e) {
            throw new ReservationNotFoundException("Failed to get list of reservations: " + e.getMessage());
        }
    }


    //Get a property by id.
    public Optional<ReservationDto> getAReservationById(Integer id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        return reservationOptional.map(ReservationMapper.INSTANCE::ReservationEntityToReservationDto);
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
                    default:
                        throw new FieldNotFoundException("Field " + entry.getKey() + " not recognized");
                }
            }
            reservationRepository.save(reservation);
        }

    }

    @Transactional
    public void updatePut(Integer propertyId, Integer reservationId, Reservation updatedReservation) {
        if (updatedReservation.getCheckInDate().isAfter(updatedReservation.getCheckOutDate())) {
            throw new PeriodIsNotAvailableException("Check-in date cannot be after check-out date");
        }

        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isEmpty()) {
            throw new PropertyNotFoundException("Property not found");
        }
        Property property = optionalProperty.get();

        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            throw new ReservationNotFoundException("Reservation not found");
        }
        Reservation existingReservation = optionalReservation.get();

        if (!property.getReservations().contains(existingReservation)){
            throw  new ReservationNotFoundException("The reservation for this property doesn't exist. ");
        }else {
            property.getReservations().remove(existingReservation);
        }

        if (!reservationRepository.overlappingReservation(propertyId, reservationId, updatedReservation.getCheckInDate(), updatedReservation.getCheckOutDate())) {
            long daysBetween = ChronoUnit.DAYS.between(updatedReservation.getCheckInDate(), updatedReservation.getCheckOutDate());
            updatedReservation.setTotalPrice(property.getPricePerNight() * daysBetween);
        } else {
            throw new PeriodIsNotAvailableException("The period for reservation is not available.");
        }
        reservationRepository.delete(existingReservation);
        updatedReservation.setProperty(property);
        reservationRepository.save(updatedReservation);
    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }
}