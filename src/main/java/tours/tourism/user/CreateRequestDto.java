package tours.tourism.user;

import jakarta.validation.constraints.NotNull;

public record CreateRequestDto(
        @NotNull String name,
        String email,
        @NotNull String password
//        @NotNull String passwordCheck
) {
}
