package tours.tourism.user;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull String email,
        @NotNull String password
) {
}
