package tours.tourism.review;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 등록
    @PostMapping
    public ReviewResponseDto create(@Valid @RequestBody CreateRequestDto request) {

        return reviewService.create(request);
    }

    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public UpdateResponseDto updateReview(
            @PathVariable("reviewId") Long reviewId,
            @RequestBody UpdateRequestDto request) {

        return reviewService.updateReview(reviewId, request);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable("reviewId") Long reviewId) {

        return reviewService.deleteReview(reviewId);
    }



}
