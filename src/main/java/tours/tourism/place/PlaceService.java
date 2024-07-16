package tours.tourism.place;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    //등록
    public String create(CreateRequestDto request) {

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

        return "등록 완료되었습니다.";
    }

    //수정
    @Transactional
    public String update(Long id, UpdateRequestDto request) {
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
        return "수정 완료되었습니다.";
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

    //상세 조회
    public PlaceDetailResponseDto findById(Long placeId) {
        Place place = placeRepository.findById(placeId).orElse(null);

        if (place == null){
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

    //삭제
    public String deleteById(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(()-> new NoSuchElementException("잘못된 접근입니다."));
        place.delete();
        return "삭제 되었습니다.";
    }
}
