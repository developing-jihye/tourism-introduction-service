package tours.tourism.review;

import jakarta.persistence.*;
import tours.tourism.place.Place;
import tours.tourism.user.User;

@Entity
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating;

    private String comment;

    @ManyToOne
    private Place place;

    @ManyToOne
    private User user;

    public Review(Double rating, String comment, Place place, User user) {
        this.rating = rating;
        this.comment = comment;
        this.place = place;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Place getPlace() {
        return place;
    }

    public User getUser() {
        return user;
    }
}
