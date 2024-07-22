package tours.tourism.user;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tours.tourism.JwtProvider;
import tours.tourism.LoginUser;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping("/join")
    public void register(@Valid @RequestBody CreateRequestDto request) {
        userService.register(request);
    }

    // 로그인
    @PostMapping("/login")
    public LoginResponse login(@LoginUser User user, @RequestBody LoginRequestDto request) {
        return userService.login(user, request);
    }

    //비밀번호 변경
    @PatchMapping("/password")
    public void changePassword(@LoginUser User user,@RequestBody ChangePasswordRequestDto request){
        userService.changePassword(user,request);
    }

    //회원 탈퇴
    @DeleteMapping("/cancel")
    public void cancelUser(@LoginUser User user,@RequestBody CancelRequestDto request){
        userService.cancelUser(user,request);
    }

}
