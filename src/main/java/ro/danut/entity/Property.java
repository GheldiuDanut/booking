package ro.danut.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private  int pricePerNight;

    @Column(nullable = false, unique = true)
    private String address;

    @Column(nullable = false)
    private String touristAttraction;

    @Column(nullable = false)
    private  String facilities;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

//    public boolean isAvailableInIntervalOfTime(LocalDate checkIn, LocalDate checkOut){
//        for ( Reservation reservation: reservations) {
//            if (reservation.getCheckInDate().isBefore(checkIn) && reservation.getCheckOutDate().isAfter(checkOut)||
//            reservation.getCheckInDate().isBefore(checkOut)&&reservation.getCheckOutDate().isAfter(checkOut)){
//                return  true;
//            }
//        }
//        return false;
//    }
}