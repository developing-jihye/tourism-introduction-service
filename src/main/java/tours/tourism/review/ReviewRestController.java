package tours.tourism.review;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tours.tourism.LoginUser;
import tours.tourism.user.User;

@RestController
@RequestMapping("/review")
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 등록
    @PostMapping
    public ReviewResponseDto create(@LoginUser User user, @Valid @RequestBody CreateRequestDto request) {

        return reviewService.create(user.getEmail(), request);
    }

    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public UpdateResponseDto updateReview(
            @LoginUser User user,
            @PathVariable("reviewId") Long reviewId,
            @RequestBody UpdateRequestDto request) {

        return reviewService.updateReview(user.getEmail(),reviewId, request);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public String deleteReview(@LoginUser User user, @PathVariable("reviewId") Long reviewId) {

        return reviewService.deleteReview(user.getEmail(), reviewId);
    }



}
