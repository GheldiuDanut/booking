package ro.danut.dto;
import lombok.Data;
import ro.danut.entity.Property;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Integer id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private double totalPrice;

    private Property property;
}
