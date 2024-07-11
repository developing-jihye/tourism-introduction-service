package tours.tourism.place;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/places")
    List<PlaceResponseDto> findAll(){
        return placeService.findAll();
    }

    @DeleteMapping("place/{placeId}")
    public void delete(@PathVariable Long placeId){
        placeService.deleteById(placeId);
    }

    @GetMapping("place/{placeId}")
    public PlaceDetailResponseDto findById(@PathVariable Long placeId){
      return placeService.findById();
    }

}
