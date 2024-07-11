package tours.tourism.review;

import jakarta.persistence.*;
import tours.tourism.place.Place;
import tours.tourism.user.User;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating;

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Place place;

    public Review() {
    }

    public Review(Double rating, String comment, User user, Place place) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.place = place;
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

    public void reviewUpdate(Double rating, String comment) {

        this.rating = rating;
        this.comment = comment;
    }
}
