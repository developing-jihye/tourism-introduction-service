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
    public String create(@Valid @RequestBody CreateRequestDto request){
        return placeService.create(request);
    }

    //수정
    @PutMapping("/place/{placeId}")
    public String update(@PathVariable Long placeId ,@Valid @RequestBody UpdateRequestDto request){
        return placeService.update(placeId,request);
    }

    //목록 조회
    @GetMapping("/places")
    public List<PlaceResponseDto> findAll(@RequestParam(required = false)City city,
                                          @RequestParam(required = false)Category category,
                                          @RequestParam(required = false)String sort){
        return placeService.findAll(city, category, sort);
    }

    //상세 조회
    @GetMapping("/places/{placeId}")
    public PlaceDetailResponseDto findById(@PathVariable Long placeId){
        return placeService.findById(placeId);
    }

    //삭제
    @DeleteMapping("/place/{placeId}")
    public String delete(@PathVariable Long placeId){
        return placeService.deleteById(placeId);
    }


}
