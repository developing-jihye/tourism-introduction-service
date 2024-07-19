package tours.tourism.user;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tours.tourism.JwtProvider;
import tours.tourism.LoginUser;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    // 회원 가입
    @PostMapping("/join")
    public void register(@Valid @RequestBody CreateRequestDto request) {
        userService.register(request);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@LoginUser User user, @RequestBody LoginRequestDto request) {
        return jwtProvider.createToken(user.getEmail());
    }

    //비밀번호 변경
    @PatchMapping("/password")
    public String changePassword(@LoginUser User user,@RequestBody ChangePasswordRequestDto request){
        return userService.changePassword(user,request);
    }

}
