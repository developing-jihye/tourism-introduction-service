package tours.tourism.review;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateRequestDto(
        @NotNull @PositiveOrZero Double rating,
        @NotNull String comment
) {
}
