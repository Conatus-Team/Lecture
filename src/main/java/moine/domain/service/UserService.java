package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.User;
import moine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // create
    public User postUser(String userName, String userNickname) {
        User user = new User();

        user.setUserName(userName);
        user.setUserNickname(userNickname);


        this.userRepository.save(user);

        return user;
    }

    // DB에서 가져오기
    public User getUser(Long userId) {
        // 크롤링 데이터 불러오기
        User user = userRepository.findById(userId).get();

        return user;
    }
}
