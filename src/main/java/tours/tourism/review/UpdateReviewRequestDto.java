package tours.tourism.review;

public record UpdateReviewRequestDto(
        Double rating,
        String comment
) {
}
