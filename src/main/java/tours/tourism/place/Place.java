package tours.tourism.place;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "place", cascade = CascadeType.PERSIST)
    List<Review> review = new ArrayList<>();

    public Place() {
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
}
