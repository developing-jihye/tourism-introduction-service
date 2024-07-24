package tours.tourism.review;

public record CreateReviewRequestDto(
        double rating,
        String comment,
        Long placeId
) {
}
