package ro.danut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Booking {
	public static void main(String[] args) {
		SpringApplication.run(Booking.class, args);
	}
}