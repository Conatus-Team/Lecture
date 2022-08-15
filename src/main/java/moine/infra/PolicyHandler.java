package moine.infra;

import lombok.RequiredArgsConstructor;
import moine.config.kafka.KafkaProcessor;
import moine.domain.entity.LectureRecommend;
import moine.domain.entity.User;
import moine.domain.event.LectureRecommended;
import moine.domain.event.SignedUp;
import moine.domain.service.RecommendService;
import moine.domain.service.UserService;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class PolicyHandler{
    private final RecommendService recommendService;
    private final UserService userService;

//
//    @StreamListener(KafkaProcessor.INPUT)
//    public void whatever(@Payload String string) {
//        System.out.println(string);
//    }

    // ** 구독 **
    // Auth로 부터 회원가입 유저 구독
    @StreamListener(KafkaProcessor.INPUT)
    public void postUser(@Payload SignedUp signedUp) {
//         if (!signedUp.validate()) throw new RuntimeException();
         if (!signedUp.validate()) return;

        System.out.println("=============================================");
        System.out.println("=================//signedUp====================");
        System.out.println(signedUp);
        System.out.println("=============================================");
        System.out.println("=============================================");
        User newUser = userService.postUser(
                signedUp.getUserId(),
                signedUp.getEmail(),
                signedUp.getUserName(),
                signedUp.getUserNickname()
        );

        System.out.println("Lecture 사용자 회원가입 성공!");
        System.out.println(newUser);
        System.out.println("=================signedUp//====================");
    }


    // RecommendSystem으로 부터 추천 강의 구독
    @StreamListener(KafkaProcessor.INPUT)
    public void updateLectureRecommended(@Payload LectureRecommended lectureRecommended){
         if(!lectureRecommended.validate()) return;
        System.out.println("================================================");
        System.out.println("=============//letureRecommended================");
        System.out.println(lectureRecommended);
        System.out.println("================================================");
        System.out.println("================================================");
        LectureRecommend newRecommendLecture = recommendService.postRecommend(
                lectureRecommended.getLectureId(),
                lectureRecommended.getUserId()
        );

        System.out.println("추천 강의 저장 성공!");
        System.out.println(newRecommendLecture);
        System.out.println("================================================");
        System.out.println("===============letureRecommended//===============");
    }


}


