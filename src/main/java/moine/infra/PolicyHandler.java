package moine.infra;

import lombok.RequiredArgsConstructor;
import moine.config.kafka.KafkaProcessor;
import moine.domain.dto.SignUpDto;
import moine.domain.entity.User;
import moine.domain.event.LetureRecommended;
import moine.domain.event.SignedUp;
import moine.domain.repository.LectureRepository;
import moine.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class PolicyHandler{
    @Autowired
    LectureRepository lectureRepository;

    private final UserService userService;

//    @StreamListener(KafkaProcessor.INPUT)
//    public void whatever(@Payload String eventString){}

    // ** 발행 **

    // RecommendSystem에게 강의 클릭 정보 발행


    // RecommendSystem에게 찜한 강의 정보 발행

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String string) {
        System.out.println("============================================================");
        System.out.println("============================================================");
        System.out.println(string);
        System.out.println("============================================================");
        System.out.println("============================================================");
    }

    // ** 구독 **
    // Auth로 부터 회원가입 유저 구독
    @StreamListener(KafkaProcessor.INPUT)
    public void postUser(@Payload SignedUp signedUp) {
        // if (!signedUp.validate()) throw new RuntimeException();

        System.out.println(signedUp);
        User newUser = userService.postUser(
                signedUp.getUserId(),
                signedUp.getEmail(),
                signedUp.getUserName(),
                signedUp.getUserNickname()
        );

        System.out.println("Lecture 사용자 회원가입 성공!");
        System.out.println(newUser);

//        userService.postUser2(signedUp);

    }


    // RecommendSystem으로 부터 추천 강의 구독




//    @StreamListener(KafkaProcessor.INPUT)
//    public void wheneverLetureRecommended_UpdateRecommendedLecture(@Payload LetureRecommended letureRecommended){
//
//        System.out.println("안녕");
//
//        if(!letureRecommended.validate()) return;
//        LetureRecommended event = letureRecommended;
//        System.out.println("\n\n##### listener UpdateRecommendedLecture : " + letureRecommended.toJson() + "\n\n");
//
//
//
//
//        // Sample Logic //
//        System.out.println("크크크ㅡㅡㅡㅋ....");
//
//        Lecture.updateRecommendedLecture(event);
//
//
//
//
//    }


}


