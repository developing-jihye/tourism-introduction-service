package tours.tourism.review;

public record ReviewResponseDTO(
        Double rating,
        String comment,
        String placeName
) {
}
