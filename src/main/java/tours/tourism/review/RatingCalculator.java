package tours.tourism.review;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.OptionalDouble;

// 평점 구하는 클래스
@Component
public class RatingCalculator {

    // 평균 평점 구하는 함수
    public double calculateAverageRating(List<Double> ratings) {

        OptionalDouble average = ratings.stream()
                .mapToDouble(Double::doubleValue)
                .average();

        double arr = average.isPresent() ? average.getAsDouble() : 0.0;
        return Math.round(arr * 1000) / 1000.0;
    }

    // 새로운 평점이 추가 되었을때 평균 평점 구하는 함수
    public double addRating(double currentAverage, int totalRatings, double newRating) {
        double newAverage = ((currentAverage * totalRatings) + newRating) / (totalRatings + 1);
        return Math.round(newAverage * 1000) / 1000.0;
    }

    // 평점이 수정 되었을 때 평균 평점 구하는 함수
    public double updateRating(double currentAverage, int totalRatings, double oldRating, double newRating) {
        double newAverage = ((currentAverage * totalRatings) - oldRating + newRating) / totalRatings;
        return Math.round(newAverage * 1000) / 1000.0;
    }

    // 평점이 삭제 되었을 때 평균 평점 구하는 함수
    public double removeRating(double currentAverage, int totalRatings, double ratingToRemove) {
        if (totalRatings <= 1) { // 전체 평점이 1개 이하일 경우 평점 0.0으로 return
            return 0.0;
        }
        double newAverage = ((currentAverage * totalRatings) - ratingToRemove) / (totalRatings - 1);
        return Math.round(newAverage * 1000) / 1000.0;
    }
}
