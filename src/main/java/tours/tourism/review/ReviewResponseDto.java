package tours.tourism.review;

public record ReviewResponseDto(
        Double rating,
        String comment,
        String placeName
) {
}
