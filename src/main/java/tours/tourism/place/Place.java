package tours.tourism.place;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault(value = "false")
    private boolean deleted = false;

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

    public void delete(){
        this.deleted = true;
    }

}
