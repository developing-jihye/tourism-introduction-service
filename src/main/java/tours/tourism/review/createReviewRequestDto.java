package tours.tourism.review;

public record createReviewRequestDto(
        double rating,
        String comment,
        Long placeId
) {
}
