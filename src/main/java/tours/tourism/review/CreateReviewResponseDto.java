package tours.tourism.review;

public record CreateReviewResponseDto(
        double rating,
        String comment,
        String placeName
) {
}
