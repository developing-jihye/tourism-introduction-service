package tours.tourism.review;

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
    ReviewResponseDTO create(@RequestBody ReviewCreateRequestDTO request) {

        return reviewService.create(request);
    }
}
