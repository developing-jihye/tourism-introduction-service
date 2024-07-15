package tours.tourism.review;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tours.tourism.place.Place;
import tours.tourism.place.PlaceRepository;
import tours.tourism.user.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    public ReviewService(ReviewRepository reviewRepository, PlaceRepository placeRepository) {
        this.reviewRepository = reviewRepository;
        this.placeRepository = placeRepository;
    }

    // 리뷰 등록
    public ReviewResponseDTO create(CreateRequestDto request) {

        Place place = new Place();

        User user = new User();

        Review review = new Review(
                request.rating(),
                request.comment(),
                user,
                place
        );

        Review reviewSave = reviewRepository.save(review);

        return new ReviewResponseDTO(
                reviewSave.getRating(),
                reviewSave.getComment(),
                reviewSave.getPlace().getName()
        );
    }

    @Transactional
    // 리뷰 수정
    public UpdateResponseDto updateReview(Long reviewId, UpdateRequestDto request) {

        Review review = reviewRepository.findById(reviewId)
                .orElse(null);

        if (review == null) {
            throw new NoSuchElementException("해당 리뷰가 존재하지 않습니다. 리뷰 ID" + reviewId);
        }

        review.reviewUpdate(
                request.rating(),
                request.comment()
        );

        return new UpdateResponseDto(
                review.getRating(),
                review.getComment(),
                review.getPlace().getName()
        );
    }

    @Transactional
    // 리뷰 삭제
    public String deleteReview(Long reviewId) {

        if (reviewId == null) {
            throw new NoSuchElementException("해당하는 리뷰가 존재하지 않습니다");
        }

        reviewRepository.deleteById(reviewId);

        return "리뷰가 삭제되었습니다";
    }

    public double calculateAverageRating(Long placeId) {

        List<Review> reviews = reviewRepository.findByPlaceId(placeId);

        OptionalDouble average = reviews.stream()
                .mapToDouble(Review::getRating)
                .average();

        double arr =average.isPresent() ? average.getAsDouble() : 0.0;
        return Math.round(arr*1000) / 1000.0;

    }
}
