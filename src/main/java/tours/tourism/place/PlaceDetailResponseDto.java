package tours.tourism.place;

public record PlaceDetailResponseDto(
        String name,
        String imageUrl,
        double rating,
        String address,
        String time,
        String description,
        String phoneNumber,
        City city,
        Category category,
        String website
//        List<Review> review
) {
}
