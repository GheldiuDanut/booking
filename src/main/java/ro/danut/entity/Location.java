package ro.danut.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private  int price;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String touristAttraction;

    @Column(nullable = false)
    private  String facilities;


    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Booking> bookings;


}
