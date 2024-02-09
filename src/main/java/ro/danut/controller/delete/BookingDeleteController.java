package ro.danut.controller.delete;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.danut.service.BookingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingDeleteController {

    private  final BookingService bookingService;

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id ){
        bookingService.deleteById(id);
    }


    @DeleteMapping("/delete/{name}")
    @Transactional
    public void deleteByName(@PathVariable String name){
        bookingService.deleteByName(name);
    }



}
