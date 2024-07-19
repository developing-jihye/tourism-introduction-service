package tours.tourism.place;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import tours.tourism.review.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String imageUrl;

    private String address;

    @Column(name = "times")
    private String time;

    private String description;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String website;

    private double rating;

    @ColumnDefault(value = "0") // ( 리뷰 전체 개수 컬럼 )
    private int reviewCount;


    @ColumnDefault(value = "false")
    private boolean deleted;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    protected Place() {
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public Category getCategory() {
        return category;
    }

    public String getWebsite() {
        return website;
    }

    public double getRating() {
        return rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Place(String name, String imageUrl, String address, String time, String description, String phoneNumber, City city, Category category, String website) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.address = address;
        this.time = time;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.category = category;
        this.website = website;
    }

    public void update(String name,
                        String imageUrl,
                        String address,
                        String time,
                        String description,
                        String phoneNumber,
                        City city,
                        Category category,
                        String website){
        this.name = name;
        this.imageUrl = imageUrl;
        this.address = address;
        this.time = time;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.category = category;
        this.website = website;

    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void incrementReviewCount() {
        this.reviewCount++;
    }

    public void decrementReviewCount() {
        this.reviewCount--;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        review.setPlace(this);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        review.setPlace(null);
    }

    public void deleteRecover(){
        this.deleted = !deleted;
    }

}
