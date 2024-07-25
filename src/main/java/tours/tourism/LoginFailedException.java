package tours.tourism;

public class LoginFailedException extends RuntimeException{

    public LoginFailedException() {
        super("로그인 정보가 유효하지 않습니다.");
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
