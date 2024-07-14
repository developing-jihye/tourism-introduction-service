package tours.tourism.review;

public record UpdateResponseDto(
        Double rating,
        String comment,
        String placeName
) {
}
