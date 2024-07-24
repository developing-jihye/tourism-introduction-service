package tours.tourism.review;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingUtils {

    private final ReviewRepository reviewRepository;

    public RatingUtils(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 평점 계산 함수
    public double getAverageRating(Long placeId) {
        List<Review> reviews = reviewRepository.findByPlaceId(placeId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }
}
