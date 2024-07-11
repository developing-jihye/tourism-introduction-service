package tours.tourism.review;

public record createReviewResponseDto(
        double rating,
        String comment,
        String placeName
) {
}
