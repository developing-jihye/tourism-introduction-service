package tours.tourism.place;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
    //장소 하나 조회
    public PlaceDetailResponseDTO findOne(Long placeId) {
        Place place = placeRepository.findById(placeId).orElse(null);
        if(place == null){
            throw new NoSuchElementException("장소를 찾을 수 없습니다. 장소 ID : "+ placeId);
        }
        return new PlaceDetailResponseDTO(
                place.getName(),
                place.getImageUrl(),
                place.getRating(),
                place.getAddress(),
                place.getTime(),
                place.getDescription(),
                place.getPhoneNumber(),
                place.getCity(),
                place.getCategory(),
                place.getWebsite(),
                null//place.getReview
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

    //수정
    public void update(Long id, UpdateRequestDto request) {
        Place place =  placeRepository.findById(id).orElse(null);
        if(place == null){
            throw new NoSuchElementException("장소를 찾을 수 없습니다.");
        }
        place.update(
                request.name(),
                request.imageUrl(),
                request.address(),
                request.time(),
                request.description(),
                request.phoneNumber(),
                request.city(),
                request.category(),
                request.website()
        );
    }


}
