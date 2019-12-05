package woowahan.anifarm.tecolearning.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "email", length = 60, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "nick_name", nullable = false, length = 10)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;

    @Column(name = "introduction", nullable = false, length = 300)
    private String introduction;

    @Builder
    public User(Long id, String email, String password, String nickName, String introduction) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.introduction = introduction;
    }

    public void update(User updateUser) {
        this.nickName = updateUser.nickName;
        this.introduction = updateUser.introduction;
    }

    public void deactivate() {
        this.status = AccountStatus.INACTIVE;
    }
}
