package tours.tourism;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import tours.tourism.user.UserRepository;

import java.util.NoSuchElementException;

@Component
public class LoginUserResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public LoginUserResolver(JwtProvider jwtProvider, UserRepository userRepository) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = webRequest.getHeader(HttpHeaders.AUTHORIZATION);

        //여기부터 중복 코드
        String[] tokenFormat = authorization.split(" ");
        String tokenType = tokenFormat[0];
        String token = tokenFormat[1];

        if (!tokenType.equals("Bearer")) {
            throw new LoginFailedException("로그인 정보가 유효하지 않습니다.");
        }
        if (!jwtProvider.isValidToken(token)) {
            throw new LoginFailedException("로그인 정보가 유효하지 않습니다.");
        }
        String userEmail = jwtProvider.getSubject(token);
        //여기까지 중복 코드

        return userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new NoSuchElementException("다시 입력해주세요."));
    }
}
