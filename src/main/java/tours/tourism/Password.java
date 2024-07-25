package tours.tourism;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Embeddable;

import java.io.IOException;
import java.util.Objects;

@JsonDeserialize(using = Password.PasswordDeserializer.class)
@JsonSerialize(using = Password.Passwordserializer.class)
@Embeddable
public class Password {

    private String value;

    protected Password() {}

    public Password(String value) {
        this.value = SecurityUtils.sha256Encrypt(value) ;
    }

    public String getValue() {
        return value;
    }

    //password 타입을 class -> text로 바꿔줌(커스텀)
    public static class PasswordDeserializer extends JsonDeserializer<Password> {
        @Override //덮어쓰다
        public Password deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            return createForRegister(jsonParser.getText());
        }
    }

    public static class Passwordserializer extends JsonSerializer<Password> {
        @Override
        public void serialize(Password password, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(password.value);
        }
    }

    //외부에서만
    public static Password createForRegister(String value){
        //로그인때 불필요 -> 회원가입용으로 옮김
        if( value.length() > 6 ){
            throw new IllegalArgumentException(" PW는 6자 이상만 가능합니다. ");
        }
        return new Password(SecurityUtils.sha256Encrypt(value));
    }

    //자동커스텀 password value끼리 비교
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
