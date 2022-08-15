package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.entity.User;
import moine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean validationUser(long userId) {
        // 값 존재
        if(userRepository.existsByUserId(userId)){
            return false;
        }

        return true;
    }

    // create
    public User postUser(long userId, String email, String userName, String userNickname) {
        if(validationUser(userId)){
            User user = new User();

            user.setUserId(userId);
            user.setEmail(email);
            user.setUserName(userName);
            user.setUserNickname(userNickname);

            User result = this.userRepository.save(user);

            return result;
        }
        else{
            return null;
        }

    }



    // DB에서 가져오기
    public User getUserById(Long userId) {
        // 크롤링 데이터 불러오기
        User user = userRepository.findByUserId(userId);

        return user;
    }

    // delete all
    public String deleteUser() {
        userRepository.deleteAll();

        return "모든 사용자 삭제 성공";
    }
}
