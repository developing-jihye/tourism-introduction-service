package tours.tourism;

import tours.tourism.review.Review;
import tours.tourism.review.ReviewRepository;

import java.util.List;
import java.util.OptionalDouble;

// 리뷰 평균 구하는 클래스
public class RatingCalculator {

    private final ReviewRepository reviewRepository;

    public RatingCalculator(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public double calculateAverageRating(Long placeId) {
        List<Review> reviews = reviewRepository.findByPlaceId(placeId);

        OptionalDouble average = reviews.stream()
                .mapToDouble(Review::getRating)
                .average();

        double arr = average.isPresent() ? average.getAsDouble() : 0.0;
        return Math.round(arr * 1000) / 1000.0;
    }

}
