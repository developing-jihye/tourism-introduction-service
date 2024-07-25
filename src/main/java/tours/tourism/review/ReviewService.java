package tours.tourism.review;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tours.tourism.place.Place;
import tours.tourism.place.PlaceRepository;
import tours.tourism.user.User;
import tours.tourism.user.UserRepository;

import java.util.NoSuchElementException;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final RatingCalculator ratingCalculator;

    public ReviewService(ReviewRepository reviewRepository, PlaceRepository placeRepository, UserRepository userRepository, RatingCalculator ratingCalculator) {
        this.reviewRepository = reviewRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.ratingCalculator = ratingCalculator;
    }

    // 리뷰 등록
    public ReviewResponseDto create(String userEmail, CreateRequestDto request) {

        Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다."));

        // 임시로 만든 빈 user ( 꼭 빼야함!!! )
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NoSuchElementException("해당 유저는 없습니다"));

        // 임시로 DB에 저장 (나중에 확인)
//        user = userRepository.save(user);

        Review review = new Review(
                request.rating(),
                request.comment(),
                user,
                place
        );

        Review reviewSave = reviewRepository.save(review);

        double newRating = ratingCalculator.addRating(place.getRating(), place.getReviewCount(), reviewSave.getRating());
        place.setRating(newRating);
        place.incrementReviewCount();
        place.addReview(reviewSave);
        placeRepository.save(place);

        return new ReviewResponseDto(
                reviewSave.getRating(),
                reviewSave.getComment(),
                reviewSave.getPlace().getName()
        );
    }

    @Transactional
    // 리뷰 수정
    public UpdateResponseDto updateReview(String userEmail, Long reviewId, UpdateRequestDto request) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("잘못 입력했습니다"));

        if (reviewId == null) {
            throw new NoSuchElementException("리뷰 ID가 존재하지 않습니다.");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElse(null);

        if (review == null) {
            throw new NoSuchElementException("리뷰가 존재하지 않습니다.");
        }

        double oldRating = review.getRating();

        review.reviewUpdate(
                request.rating(),
                request.comment()
        );

        Place place = review.getPlace();
        double newRating = ratingCalculator.updateRating(place.getRating(), place.getReviewCount(), oldRating, review.getRating());
        place.setRating(newRating);
        placeRepository.save(place);

        return new UpdateResponseDto(
                review.getRating(),
                review.getComment(),
                review.getPlace().getName()
        );
    }

    // 리뷰 삭제
    @Transactional
    public String deleteReview(String userEmail, Long reviewId) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("잘못 입력했습니다"));

        if (reviewId == null) {
            throw new NoSuchElementException("해당하는 리뷰가 존재하지 않습니다");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElse(null);

        if (review == null) {
            throw new NoSuchElementException("해당 리뷰가 없습니다");
        }

        Place place = review.getPlace();
        double newRating = ratingCalculator.removeRating(place.getRating(), place.getReviewCount(), review.getRating());
        place.setRating(newRating);
        place.decrementReviewCount();
        place.removeReview(review);
        placeRepository.save(place);

        review.deletedDateTime();

        return "리뷰가 삭제되었습니다";
    }

}
