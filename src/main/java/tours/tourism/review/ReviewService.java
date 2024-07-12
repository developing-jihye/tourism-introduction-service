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

    // 리뷰 등록
    public ReviewResponseDTO create(ReviewCreateRequestDTO request) {

        Place place = placeRepository.findById(request.placeId())
                .orElse(null);

        if (place == null) {
            throw new NoSuchElementException("해당 하는 아이디의 장소가 존재하지 않습니다.");
        }

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
    public String updateReview(Long reviewId, ReviewUpdateRequestDTO request) {

        Review review = reviewRepository.findById(reviewId)
                .orElse(null);

        if (review == null) {
            throw new NoSuchElementException("해당 리뷰가 존재하지 않습니다. 리뷰 ID" + reviewId);
        }

        review.reviewUpdate(
                request.rating(),
                request.comment()
        );

        return "수정 완료 되었습니다";
    }
}
