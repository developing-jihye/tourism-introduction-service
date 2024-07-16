package tours.tourism.user;

import org.springframework.stereotype.Service;
import tours.tourism.SecurityUtils;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void login(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.email())
                .orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다");
        }

        if (user.authenticate(SecurityUtils.sha256Encrypt(request.password()))) {
            throw  new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다");
        }
    }
}
