package tours.tourism.review;

import org.springframework.data.jpa.repository.JpaRepository;
import tours.tourism.place.Place;

import java.util.Arrays;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
