package ro.danut.controller.get;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.danut.entity.Location;
import ro.danut.service.BookingService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingGetController {

    private final BookingService bookingService;


    @GetMapping("/locations")
    public List<Location> getAllLocations(){
        return bookingService.getAllLocations();
    }


    @GetMapping("/locations/{attraction}")
    public List<Location> getAllLocationsForAnTouristAttraction(@PathVariable String attraction){
        return bookingService.getAllLocations().stream().filter(location -> location.getTouristAttraction().contains(attraction)).toList();
//        return bookingService.getAllLocationsByTouristAttraction();
    }

    @GetMapping("/location/{price}")
    public List<Location>getAllLocationForAnCertainPrice(@PathVariable int price){
        return  bookingService.getAllLocations().stream()
                .filter(location -> location.getPrice() == price)
                .collect(Collectors.toList());
    }
}
