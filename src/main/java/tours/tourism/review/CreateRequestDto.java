package tours.tourism.review;

import jakarta.validation.constraints.NotNull;

public record CreateRequestDto(
        @NotNull Double rating,
        @NotNull String comment,
        @NotNull Long userId
) {
}
