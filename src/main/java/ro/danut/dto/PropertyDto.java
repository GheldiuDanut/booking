package ro.danut.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ro.danut.entity.Reservation;
import java.util.List;
@Data
public class PropertyDto {
    private Integer id;

    private String name;

    private String description;

    private int pricePerNight;

    private String address;

    private String touristAttraction;

    private String facilities;

    @JsonIgnoreProperties("property")
    private List<Reservation> reservations;
}