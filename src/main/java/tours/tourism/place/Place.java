package tours.tourism.place;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private String phoneNumber;

    private String city;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String website;

    private double rating;

    private String time;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
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

    public String getTime() {
        return time;
    }
}
