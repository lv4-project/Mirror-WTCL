package woowahan.anifarm.tecolearning.user.service;

import org.springframework.stereotype.Service;
import woowahan.anifarm.tecolearning.user.domain.User;
import woowahan.anifarm.tecolearning.user.domain.UserRepository;
import woowahan.anifarm.tecolearning.user.dto.UserCreateDto;
import woowahan.anifarm.tecolearning.user.dto.UserInfoDto;
import woowahan.anifarm.tecolearning.user.dto.UserUpdateDto;
import woowahan.anifarm.tecolearning.user.exception.UserCreateException;
import woowahan.anifarm.tecolearning.user.exception.UserNotFoundException;

import javax.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoDto save(UserCreateDto userCreateDto) {
        try {
            return UserInfoDto.from(userRepository.save(userCreateDto.toEntity()));
        } catch (Exception error) {
            throw new UserCreateException(error);
        }
    }

    public UserInfoDto findInfoDtoById(long userId) {
        return UserInfoDto.from(findById(userId));
    }

    public User findById(long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public UserInfoDto update(UserUpdateDto userUpdateDto, long userId) {
        User findUser = findById(userId);
        findUser.update(userUpdateDto.toEntity());

        return UserInfoDto.from(findUser);
    }

    @Transactional
    public void delete(long userId) {
        userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new)
                .deactivate();
    }
}
