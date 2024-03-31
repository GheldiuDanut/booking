package ro.danut.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.danut.dto.PropertyDto;
import ro.danut.entity.Property;
@Mapper
public interface PropertyMapper {

    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    Property PropertyDtoToPropertyEntity(PropertyDto propertyDto);

    PropertyDto PropertyEntityToPropertyDto(Property property);
}


