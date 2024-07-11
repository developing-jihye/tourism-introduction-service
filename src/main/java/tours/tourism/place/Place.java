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

    private String address;

    private String time;

    private String description;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String website;

    private double rating;

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
