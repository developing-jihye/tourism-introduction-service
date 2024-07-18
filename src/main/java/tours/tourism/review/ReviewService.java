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

    public ReviewService(ReviewRepository reviewRepository, PlaceRepository placeRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }

    // 리뷰 등록
    public ReviewResponseDto create(CreateRequestDto request) {

        Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다."));

        // 임시로 만든 빈 user ( 꼭 빼야함!!! )
        User user = new User("홍길동", "123", "abc@gmail.com");

        // 임시로 DB에 저장
        user = userRepository.save(user);

        Review review = new Review(
                request.rating(),
                request.comment(),
                user,
                place
        );

        Review reviewSave = reviewRepository.save(review);


        return new ReviewResponseDto(
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

        review.deletedDateTime();

        return "리뷰가 삭제되었습니다";
    }

}
