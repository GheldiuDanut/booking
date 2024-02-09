package ro.danut.controller.post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.danut.entity.Location;
import ro.danut.service.LocationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingPostController implements IBookingPostController{
    private final LocationService locationService;

    public void createLocation(@RequestBody Location location){
        locationService.save(location);
    }


}
