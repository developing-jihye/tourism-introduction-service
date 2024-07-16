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

    //장소 조회
    @GetMapping("/place/{placeId}")
    public PlaceDetailResponseDTO findOne(@PathVariable Long id){
        return placeService.findOne(id);
    }

    //목록 조회
    @GetMapping("/places")
    List<PlaceResponseDto> findAll(){
        return placeService.findAll();
    }

    //수정
    @PutMapping("/place/{placeId}")
    public void update(@PathVariable Long id , @RequestBody UpdateRequestDto request){
        placeService.update(id,request);
    }


}
