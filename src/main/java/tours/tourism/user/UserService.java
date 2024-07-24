package tours.tourism.user;

import org.springframework.stereotype.Service;
import tours.tourism.JwtProvider;
import tours.tourism.SecurityUtils;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    // 회원 가입
    public void register(CreateRequestDto request) {

        userRepository.save(new User(
                request.name(),
                request.email(),
                SecurityUtils.sha256Encrypt(request.password())
        ));
    }

    // 로그인
    public User authenticate(LoginRequestDto request) {
        // 이메일 검증
        User user = userRepository.findByEmail(request.email())
                .orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다");
        }

        // 비밀번호 검증
        if (!user.authenticate(request.password())) {
            throw  new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다");
        }

        return user;
    }

    public String generateToken(User user) {
        // 주입받은 JwtProvider 오브젝트를 통해 토큰 발급
        return jwtProvider.createToken(user.getEmail());
    }

    public LoginResponseDto authenticateAndGenerateToken(LoginRequestDto requestDto) {
            User user = authenticate(requestDto);
            String token = generateToken(user);
            return new LoginResponseDto(token);
    }
}
