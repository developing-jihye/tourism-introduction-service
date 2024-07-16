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
        placeService.create(request);
        return "등록되었습니다";
    }

    //목록 조회
    @GetMapping("/places")
    public List<PlaceResponseDto> findAll(@RequestParam(required = false)String sort){
        return placeService.findAll(sort);
    }

    //수정
    @PutMapping("/place/{placeId}")
    public String update(@PathVariable Long placeId , @RequestBody UpdateRequestDto request){
       placeService.update(placeId,request);
        return "수정되었습니다.";
    }

//    //삭제(hard delete)
//    @DeleteMapping("/place/{placeId}")
//    public String delete(@PathVariable Long placeId){
//        placeService.deleteById(placeId);
//        return "삭제되었습니다.";
//    }

    //상세조회
    @GetMapping("place/{placeId}")
    public PlaceDetailResponseDto findById(@PathVariable Long placeId){
          return placeService.findById(placeId);

    }
    //soft delete
    @DeleteMapping("/place/{placeId}")
    public String deleteRecover(@PathVariable Long placeId){
        placeService.deleteRecover(placeId);
        return "삭제(복구)되었습니다";
    }


}
