package tours.tourism;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tours.tourism.place.PlaceRepository;



@SpringBootTest
public class PlaceTest {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceTest(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

//    @Test
//    public void testFindAll() {
//        // 실제 테스트 코드 작성
//        System.out.println(placeRepository.findAllByIdOrderByNameDesc(1L));
//    }

}
