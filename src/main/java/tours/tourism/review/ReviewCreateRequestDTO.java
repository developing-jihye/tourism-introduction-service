package tours.tourism.review;

import tours.tourism.place.Place;

public record ReviewCreateRequestDTO(
        Double rating,
        String comment,
        Long placeId,
        Long userId
) {
}
