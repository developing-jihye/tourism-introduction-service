package tours.tourism.place;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {
    List<PlaceResponseDto> findAll(City city,Category category,String sort);
}
