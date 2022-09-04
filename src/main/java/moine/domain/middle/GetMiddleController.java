package moine.domain.middle;


import lombok.RequiredArgsConstructor;
import moine.domain.entity.LectureRecommend;
import moine.domain.entity.User;
import moine.domain.event.LectureRecommended;
import moine.domain.event.SignedUp;
import moine.domain.service.RecommendService;
import moine.domain.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/lecture/connect_middle")
public class GetMiddleController {
    public final UserService userService;
    public final RecommendService recommendService;

    // 구독 : (Auth)회원가입
    @PostMapping("/SignedUp")
    public void postUser(@RequestBody SignedUp signedUp) {
        if (!signedUp.validate()) return;

        User newUser = userService.postUser(
            signedUp.getUserId(),
            signedUp.getEmail(),
            signedUp.getUserName(),
            signedUp.getUserNickname()
        );

    }

    // 구독 : (Recommend)추천 강의
    // TODO : Recommend 서버 실행해서 테스트하기
    public void updateLectureRecommended(@RequestBody LectureRecommended lectureRecommended){
         if(!lectureRecommended.validate()) return;
         try{
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
         catch (Exception e){
//             System.out.println(e)
             e.printStackTrace();
         }


    }

}
