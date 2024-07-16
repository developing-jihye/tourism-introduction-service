package tours.tourism.review;

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
    public CreateReviewResponseDto create(CreateReviewRequestDto createRequestDto) {

        Place place = placeRepository.findById(createRequestDto.placeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다."));

        // 임시로 만든 빈 user
        User user = new User("홍길동", "123", "abc@gmail.com");

        // 임시로 DB에 저장
        user = userRepository.save(user);

        Review review = new Review(
                createRequestDto.rating(),
                createRequestDto.comment(),
                place,
                user
        );

        reviewRepository.save(review);

        return new CreateReviewResponseDto(
                review.getRating(),
                review.getComment(),
                review.getPlace().getName()
        );
    }

    // 리뷰 수정
    public CreateReviewResponseDto update(Long reviewId, UpdateReviewRequestDto body) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("해당 리뷰가 없습니다."));
        reviewRepository.save(review);

        return new CreateReviewResponseDto(
                review.getRating(),
                review.getComment(),
                review.getPlace().getName()
        );
    }

    // 리뷰 삭제
    public void softDelete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("해당 리뷰가 없습니다."));
        review.softDelete();
    }
}
