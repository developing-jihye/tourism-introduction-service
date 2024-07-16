package tours.tourism.review;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateRequestDto(
        @NotNull @PositiveOrZero Double rating,
        @NotNull String comment,
        @NotNull Long placeId
) {
}
