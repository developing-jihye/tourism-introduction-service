package tours.tourism.review;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.OptionalDouble;

// 리뷰 평균 구하는 클래스
@Component
public class RatingCalculator {

    public double calculateAverageRating(List<Double> ratings) {

        OptionalDouble average = ratings.stream()
                .mapToDouble(Double::doubleValue)
                .average();

        double arr = average.isPresent() ? average.getAsDouble() : 0.0;
        return Math.round(arr * 1000) / 1000.0;
    }
}
