package tours.tourism.place;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PlaceResponseDto(
        @NotNull
        String name,

        String imageUrl,

        Double rating,

        LocalDateTime time,

        String PhoneNumber,

        @NotNull
        String city,

        @NotNull
        Category category,

        String website
) {
}
