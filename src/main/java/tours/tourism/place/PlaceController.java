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

    //목록 조회
    @GetMapping("/places")
    List<PlaceResponseDto> findAll(){
        return placeService.findAll();
    }

    //수정
    @PutMapping("/place/{placeId}")
    public String update( @PathVariable Long id ,@Valid @RequestBody UpdateRequestDto request){
        return placeService.update(id,request);
    }


}
