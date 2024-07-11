package tours.tourism.review;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 등록
    @PostMapping("/review")
    public createReviewResponseDto createReview(@RequestBody createReviewRequestDto createRequestDto) {
        return reviewService.create(createRequestDto);
    }

    // 리뷰 수정

    // 리뷰 삭제

}
