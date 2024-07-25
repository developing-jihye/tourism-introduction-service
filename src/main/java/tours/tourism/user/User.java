package tours.tourism.user;

import jakarta.persistence.*;
import tours.tourism.SecurityUtils;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // 비밀번호 같은지 확인하는 함수
    public boolean authenticate(String password) {
        String hashedPassword = SecurityUtils.sha256Encrypt(password);
        return this.password.equals(hashedPassword);
    }

    // 비밀번호 변경하는 함수
    public void changePassword(String password) {
        this.password = SecurityUtils.sha256Encrypt(password);
    }
}
