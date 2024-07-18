package tours.tourism.place;

import jakarta.validation.constraints.NotNull;

public record UpdateRequestDto(
        @NotNull
        String name,
        String imageUrl,
        String address,
        String time,
        String description,
        String phoneNumber,
        @NotNull
        City city,
        @NotNull
        Category category,
        String website
) {
}
