package tours.tourism.place;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
