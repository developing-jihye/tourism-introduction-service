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

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 리뷰 등록
    public ReviewResponseDTO create(CreateRequestDto request) {



        User user = new User();

        Review review = new Review(
                request.rating(),
                request.comment(),
                user
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

        if (reviewId == null) {
            throw new NoSuchElementException("리뷰 ID가 존재하지 않습니다.");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElse(null);

        if (review == null) {
            throw new NoSuchElementException("리뷰가 존재하지 않습니다.");
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

    // 리뷰 삭제
    @Transactional
    public String deleteReview(Long reviewId) {

        if (reviewId == null) {
            throw new NoSuchElementException("해당하는 리뷰가 존재하지 않습니다");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElse(null);

        if (review == null) {
            throw new NoSuchElementException("해당 리뷰가 없습니다");
        }

        review.deleteTime();

        return "리뷰가 삭제되었습니다";
    }

}
