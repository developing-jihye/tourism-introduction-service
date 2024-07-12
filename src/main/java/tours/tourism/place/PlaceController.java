package tours.tourism.place;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    //등록
    @PostMapping("/place")
    public void create(@Valid @RequestBody CreateRequestDto request){
        placeService.create(request);
    }

    //목록 조회
    @GetMapping("/places")
    List<PlaceResponseDto> findAll(){
        return placeService.findAll();
    }

    //수정
    @PutMapping("/place/{placeId}")
    public Place update(@PathVariable Long id , @RequestBody UpdateRequestDto request){
       Place place= placeService.update(id,request);
       return place;
    }

    //삭제
    @DeleteMapping("/place/{placeId}")
    public void delete(@PathVariable Long placeId){
        placeService.deleteById(placeId);
    }

    //상세조회
    @GetMapping("place/{placeId}")
    public PlaceDetailResponseDto findById(@PathVariable Long placeId){
         PlaceDetailResponseDto dto = placeService.findById(placeId);
         return dto;
    }


}
