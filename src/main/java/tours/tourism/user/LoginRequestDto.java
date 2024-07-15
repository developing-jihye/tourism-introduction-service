package tours.tourism.user;

public record LoginRequestDto(
        String email,
        String password
) {
}
