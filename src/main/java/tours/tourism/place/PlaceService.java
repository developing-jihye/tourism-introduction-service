package tours.tourism.place;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    //등록
    public void create(CreateRequestDto request) {
        placeRepository.save(new Place(request.name(),
                        request.imageUrl(),
                        request.address(),
                        request.time(),
                        request.description(),
                        request.phoneNumber(),
                        request.city(),
                        request.category(),
                        request.website())
                );
    }

    //목록 조회
    public List<PlaceResponseDto> findAll() {
        return placeRepository
                .findAll()
                .stream()
                .map(place -> new PlaceResponseDto(
                        place.getName(),
                        place.getImageUrl(),
                        place.getRating(),
                        place.getTime(),
                        place.getPhoneNumber(),
                        place.getCity(),
                        place.getCategory(),
                        place.getWebsite(),
                        place.getAddress()
                )).toList();
    }


}
