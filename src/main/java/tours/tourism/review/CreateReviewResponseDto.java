package tours.tourism.review;

public record CreateReviewResponseDto(
        Double rating,
        String comment,
        String placeName
) {
}
