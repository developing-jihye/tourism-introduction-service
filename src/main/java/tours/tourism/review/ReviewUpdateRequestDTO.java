package tours.tourism.review;

public record ReviewUpdateRequestDTO(
        Double rating,
        String comment
) {
}
