package woowahan.anifarm.tecolearning.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woowahan.anifarm.tecolearning.user.exception.UserCreateException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {
    private static final String VALID_EMAIL = "moomin@woowa.com";
    private static final String VALID_PASSWORD = "12345678";
    private static final String VALID_NICK_NAME = "moomin";

    @Test
    @DisplayName("잘못된 이메일 형식으로 유저 생성 실패")
    void invalid_email_error() {
        assertThrows(UserCreateException.class, () -> User.builder()
                .email("moo")
                .password(VALID_PASSWORD)
                .nickName(VALID_NICK_NAME)
                .build());
    }

    @Test
    @DisplayName("잘못된 비밀번호 형식으로 유저 생성 실패")
    void invalid_password_error() {
        assertThrows(UserCreateException.class, () -> User.builder()
                .email(VALID_EMAIL)
                .password("1234")
                .nickName(VALID_NICK_NAME)
                .build());
    }

    @Test
    @DisplayName("잘못된 닉네임 형식으로 유저 생성 실패")
    void invalid_nick_name_error() {
        assertThrows(UserCreateException.class, () -> User.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .nickName("a")
                .build());
    }
}