package tours.tourism.place;

import jakarta.validation.constraints.NotNull;
//import tours.tourism.review.Review;

import java.util.List;

public record PlaceDetailResponseDTO(
        @NotNull
        String name,

        String imageUrl,

        Double rating,

        String address,

        String time,

        String description,

        String phoneNumber,

        @NotNull
        City city,

        @NotNull
        Category category,

        String website

//        List<Review> reviews
){
}
