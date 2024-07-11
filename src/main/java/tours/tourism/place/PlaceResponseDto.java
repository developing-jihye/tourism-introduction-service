package tours.tourism.place;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PlaceResponseDto(
        @NotNull
        String name,

        String imageUrl,

        Double rating,

        String time,

        String PhoneNumber,

        @NotNull
        City city,

        @NotNull
        Category category,

        String website,

        String address
) {
}
