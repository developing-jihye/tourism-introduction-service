package tours.tourism.user;

import org.springframework.stereotype.Service;
import tours.tourism.JwtProvider;
import tours.tourism.SecurityUtils;

@Service
public class UserService {

    private final UserRepository userRepository;
    public final JwtProvider jwtProvider;

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
    public LoginResponse login(User user,LoginRequestDto request) {

        if (!user.authenticate(request.password())) {
            throw  new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다");
        }
        String token = jwtProvider.createToken(user.getEmail());
        return new LoginResponse(token);
    }

    //비밀번호 변경
    public String changePassword(User user, ChangePasswordRequestDto request) {
        if (!user.authenticate(request.password())) {
            throw new IllegalArgumentException("비밀번호를 다시 입력해주세요.");
        }
        if(!request.password().equals(request.passwordCheck())) {
                throw new IllegalArgumentException("맞는 비밀번호를 입력해주세요.");
            }
        return request.changePassword();

    }

    //회원 탈퇴
    public void cancelUser(User user, CancelRequestDto request) {
        if(!user.authenticate(request.password())){
            throw new IllegalArgumentException("비밀번호를 다시 입력해주세요.");
        }
        userRepository.deleteByEmail(user.getEmail());
    }
}
