package tours.tourism.user;

import jakarta.validation.constraints.NotNull;

public record CancelRequestDto(
        @NotNull String password,
        @NotNull String passwordCheck
) {
}
