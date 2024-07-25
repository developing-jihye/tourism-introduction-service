package tours.tourism.place;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tours.tourism.user.User;
import tours.tourism.user.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public PlaceService(PlaceRepository placeRepository, UserRepository userRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }

    //등록
    public void create(String userEmail, CreateRequestDto request) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("잘못 입력했습니다"));

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
        if(Objects.equals(sort, "rating")){//이름순 정렬

            places = placeRepository.findAllByDeletedFalseOrderByRatingDesc();
        }else{//디폴트로 rating 정렬
            places = placeRepository.findAllByDeletedFalseOrderByNameAsc();
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
    public void update(String userEmail, Long id, UpdateRequestDto request) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("잘못 입력했습니다"));

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
        Place place = placeRepository.findById(placeId).orElse(null);

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
                place.getWebsite(),
                place.getReviews().stream().map(
                        review -> new PlaceDetailResponseDto.Review(
                                review.getRating(),
                                review.getComment()
                        )
                ).toList()
        );
    }

    @Transactional
    public void deleteRecover(String userEmail, Long placeId) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("잘못 입력했습니다"));

        Place place = placeRepository.findById(placeId).orElse(null);
        if (place ==null){
            throw new NoSuchElementException("장소를 찾을 수 없습니다.");
        }
        place.deleteRecover();
    }
}
