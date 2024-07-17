package tours.tourism.place;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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
    @Transactional
    public List<PlaceResponseDto> findAll(String sort) {
        List<Place> places;
        if(Objects.equals(sort, "name")){//이름순 정렬
            places = placeRepository.findAllByDeletedFalseOrderByNameAsc();

        }else{//디폴트로 rating 정렬
            places = placeRepository.findAllByDeletedFalseOrderByRatingDesc();
        }
        return places
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
    @Transactional
    public void update(Long id, UpdateRequestDto request) {
        Place place = placeRepository.findById(id).orElse(null);
        if (place == null) {
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

//    //삭제(hard delete)
//    public void deleteById(Long placeId) {
//        placeRepository.deleteById(placeId);
//    }

    //상세 조회
    public PlaceDetailResponseDto findById(Long placeId) {
        Place place =placeRepository.findById(placeId).orElse(null);

        if (place ==null){
            throw new NoSuchElementException("장소를 찾을 수 없습니다.");
        }

        return new PlaceDetailResponseDto(
                place.getName(),
                place.getImageUrl(),
                place.getRating(),
                place.getAddress(),
                place.getTime(),
                place.getDescription(),
                place.getPhoneNumber(),
                place.getCity(),
                place.getCategory(),
                place.getWebsite()
        );
    }

    @Transactional
    public void deleteRecover(Long placeId) {
        Place place = placeRepository.findById(placeId).orElse(null);
        if (place ==null){
            throw new NoSuchElementException("장소를 찾을 수 없습니다.");
        }
        place.deleteRecover();
    }
}
