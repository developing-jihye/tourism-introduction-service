package tours.tourism.user;

public record ChangePasswordRequestDto(
        String password,
        String passwordCheck,
        String changePassword
) {
}
