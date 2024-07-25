package tours.tourism.user;

import jakarta.transaction.Transactional;
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

    //비밀번호 변경
    @Transactional
    public void changePassword(User user, ChangePasswordRequestDto request) {

        if (!user.authenticate(request.password())) {
            throw new IllegalArgumentException("비밀번호를 다시 입력해주세요.");
        }

        if(!request.password().equals(request.passwordCheck())) {
            throw new IllegalArgumentException("맞는 비밀번호를 입력해주세요.");
        }

        user.changePassword(request.newPassword());

    }

    //회원 탈퇴
    @Transactional
    public void cancelUser(String email, CancelRequestDto request) {

        User user = userRepository.findByEmail(email)
                .orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다");
        }
        if(!user.authenticate(request.password())){
            throw new IllegalArgumentException("비밀번호를 다시 입력해주세요.");
        }

        userRepository.deleteByEmail(user.getEmail());
    }
}
