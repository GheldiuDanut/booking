package ro.danut.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.danut.dto.PropertyDto;
import ro.danut.dto.ReservationDto;
import ro.danut.entity.Property;
import ro.danut.entity.Reservation;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Reservation ReservationDtoToReservationEntity(ReservationDto reservationDto);

    ReservationDto ReservationEntityToReservationDto(Reservation reservation);
}
