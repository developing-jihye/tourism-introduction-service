package tours.tourism.review;

import jakarta.persistence.*;
import tours.tourism.place.Place;
import tours.tourism.user.User;

import java.time.LocalDateTime;

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

    private LocalDateTime deletedDateTime = null;

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

    // 리뷰 수정 함수
    public void reviewUpdate(Double rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    // 리뷰 삭제 했을 때 시간 나오게 하는 함수
    public void deletedDateTime() {
        this.deletedDateTime = LocalDateTime.now();
    }
}
