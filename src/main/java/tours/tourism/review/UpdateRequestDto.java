package tours.tourism.review;

public record UpdateRequestDto(
        Double rating,
        String comment
) {
}
