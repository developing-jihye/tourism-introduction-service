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
    public List<PlaceResponseDto> findAll(@RequestParam(required = false)String sort){
        return placeService.findAll(sort);
    }

    //수정
    @PutMapping("/place/{placeId}")
    public Place update(@PathVariable Long placeId , @RequestBody UpdateRequestDto request){
       Place place= placeService.update(placeId,request);
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
    @PutMapping("/deleteRecover/{placeId}")
    public void deleteRecover(@PathVariable Long placeId){
        placeService.deleteRecover(placeId);
    }


}
