package tours.tourism.place;

import tours.tourism.review.Review;

import java.util.List;

public record PlaceDetailResponseDto(
        String name,
        String imageUrl,
        Double rating,
        String address,
        String time,
        String description,
        String phoneNumber,
        City city,
        Category category,
        String website,
        List<Review> reviews
) {
    public record Review(
            Double rating,
            String comment
    ) {

    }
}
