package tours.tourism.review;

import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 등록
    @PostMapping("/review")
    public CreateReviewResponseDto createReview(@RequestBody CreateReviewRequestDto createRequestDto) {
        return reviewService.create(createRequestDto);
    }

    // 리뷰 수정
    @PutMapping("/review/{reviewId}")
    public CreateReviewResponseDto updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewRequestDto body) {

        return null;
    }

    // 리뷰 삭제

}
