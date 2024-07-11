package tours.tourism.review;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tours.tourism.place.Place;
import tours.tourism.place.PlaceRepository;
import tours.tourism.user.User;

import java.util.NoSuchElementException;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    public ReviewService(ReviewRepository reviewRepository, PlaceRepository placeRepository) {
        this.reviewRepository = reviewRepository;
        this.placeRepository = placeRepository;
    }

}
