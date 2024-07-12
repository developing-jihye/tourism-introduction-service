package tours.tourism.review;

import jakarta.validation.constraints.NotNull;
import tours.tourism.place.Place;

public record ReviewCreateRequestDTO(
        @NotNull Double rating,
        @NotNull String comment,
        @NotNull Long userId
) {
}
