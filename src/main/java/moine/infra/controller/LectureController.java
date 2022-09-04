package moine.infra.controller;

import lombok.RequiredArgsConstructor;
import moine.domain.dto.*;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureDetailShow;
import moine.domain.entity.LectureLike;
import moine.domain.entity.LectureSearch;
import moine.domain.event.LectureDetailShown;
import moine.domain.event.LectureLiked;
import moine.domain.event.LectureSearched;
import moine.domain.middle.PostMiddleService;
import moine.domain.middle.Url;
import moine.domain.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/lecture")
@Transactional
public class LectureController {

    private final CrawlingService crawlingService;
    private final SearchService searchService;
    private final UserService userService;
    private final LikeService likeService;
    private final DetailShowService detailShowService;
    private final RecommendService recommendService;
    private final PostMiddleService postMiddleService;

    /*
     ********************
            관리자
     ********************
    */

    // 크롤링 실행 및 DB 저장
    @GetMapping("/crawlingSave")
    public List<LectureCrawlingVO> LectureCrawlingListSave(HttpServletRequest request){
        // lecture 전체 삭제 (중복 데이터 누적방지)
        crawlingService.deleteAllLectureCrawlingList();

        // 다시 크롤링 후 저장
        List<LectureCrawlingVO> list = crawlingService.saveLectureCrawlingList();
        return list;

    }

    // 카프카 이벤트 발송
    // Recommend System 서버로 강의 자세히보기, 찜하기, 키워드 정보 전송
    @GetMapping("/send")
    public String sendToRecommendSystem(HttpServletRequest request) {
        // 강의 자세히 보기
        List<LectureDetailShow> detailShowList = detailShowService.getAllLectureDetailShow();
        LectureDetailShown detailShownMessage = new LectureDetailShown(detailShowList);
        detailShownMessage.publish();
        postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/LectureDetailShown", detailShownMessage);

        // 찜하기
        List<LectureLike> likeList = likeService.getAllLikeList();
        LectureLiked likedMessage = new LectureLiked(likeList);
        likedMessage.publish();
        postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/LectureLiked", likedMessage);


        // 키워드
        List<LectureSearch> searchList = searchService.getAllLectureSearch();
        LectureSearched searchedMessage = new LectureSearched(searchList);
        searchedMessage.publish();
        postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/LectureSearched", searchedMessage);


        return "전송 완료";
    }

    // 사용자 모두 삭제
    @DeleteMapping("")
    public String deleteAllUser(HttpServletRequest request) {
        return userService.deleteUser();
    }




    /*
     ********************
            사용자
     ********************
    */

    // 사용자
    // 회원가입
//    @PostMapping("/signUp")
//    public User postUser(@RequestBody SignUpDto signUpDto) {
//        System.out.println("signUpDto = " + signUpDto);
//        User newUser = userService.postUser(signUpDto.getUserId(), signUpDto.getUserName(), signUpDto.getUserNickname());
//
//        return newUser;
//    }


    // 강의 첫 화면
    @GetMapping("")
    public LikeAndRecommendDto getLecture(@RequestHeader(value="Authorization") Long userId) {
        System.out.println("===================================");
        System.out.println("===================================");
        System.out.println(userId);
        System.out.println("===================================");
        System.out.println("===================================");

        // 검증
        if(!userService.existsUser(userId)){
            return null;
        }

        // 로그인 전 : 전체 강의
        if (userId == 0) {
            // 크롤링 데이터 불러오기
            List<LectureCrawling> list = crawlingService.getLectureCrawlingList();
            LikeAndRecommendDto result = new LikeAndRecommendDto();
            result.setCrawlingList(list);

            return result;

        }

        // 로그인 후 : 추천 강의, 찜한 강의만 존재 (전체 강의 보여주지 않음)
        LikeAndRecommendDto result = new LikeAndRecommendDto();
        result.setLikeList(likeService.getLikeListByUserId(userId));
        result.setRecommendList(recommendService.getRecommendListByUserId(userId));

        return result;

    }


    // 모든 강의 보기
    @GetMapping("/all")
    public LectureAllDto getLectureCrawlingList(@RequestHeader(value="Authorization") Long userId) {
        // 검증
        if(!userService.existsUser(userId)){
            return null;
        }

        if(userId == 0) {
            List<LectureCrawling> list = crawlingService.getLectureCrawlingList();
            LectureAllDto result = new LectureAllDto();
            result.setData(list);

            return result;
        }

        // 찜한 강의 여부 추가
        List<LectureCrawling> list = crawlingService.getLectureCrawlingList();
        List<Long> id_list = likeService.likeList(userId);
        LectureAllDto result = new LectureAllDto();
        result.setData(list);
        result.setLikeId(id_list);

        return result;
    }


    // 특정 강의 보기
    @PostMapping("/{lectureId}")
    public LectureCrawling getLectureItem(@PathVariable Long lectureId, @RequestHeader(value="Authorization") Long userId){
        // 검증
        if(!userService.existsUser(userId) || !crawlingService.existsLectureId(lectureId)){
            return null;
        }

        if(userId == 0) {
            return crawlingService.getLectureById(lectureId);
        }

        // lecture_detail_show 에 클릭한 강의, 유저를 저장하고 클릭 횟수 증가
        detailShowService.saveLectureDetailShow(lectureId, userId);
        return crawlingService.getLectureById(lectureId);

    }


    // 강의 검색하기
    @PostMapping("/search")
    public LectureAllDto postLectureSearchResult(@RequestParam("keyword") String keyword, @RequestHeader(value="Authorization") Long userId) {
        // 검증
        if(!userService.existsUser(userId)){
            return null;
        }

        if(userId == 0) {
            // 키워드를 포함하는 강의를 DB에서 가져오기
            List<LectureCrawling> list = searchService.search(keyword);
            LectureAllDto result = new LectureAllDto();
            result.setData(list);

            return result;
        }

//        List<LectureCrawling> results = searchService.search(keyword);
        // lecture_search 디비에 어떤 userid가 어떤 키워드를 검색했는지 저장
        searchService.saveSearch(keyword, userId);

        // 찜한 강의 여부 추가
        List<LectureCrawling> searchList = searchService.search(keyword);
        List<Long> id_list = likeService.likeList(userId);
        LectureAllDto result = new LectureAllDto();
        result.setData(searchList);
        result.setLikeId(id_list);


        return result;
    }

    // 찜하기 목록 보기
    @GetMapping("/like")
    public List<LectureLike> getLectureLikeList (@RequestHeader(value="Authorization") Long userId){
        // 검증
        if(!userService.existsUser(userId)){
            return null;
        }

        if(userId == 0){
            return null;
        }
        return likeService.getLikeListByUserId(userId);
    }

    // 강의 찜하기 추가
    @PostMapping("/like/{lectureId}")
    public LectureLike postLectureLike(@PathVariable Long lectureId, @RequestHeader(value="Authorization") Long userId) {
        // 검증
        if(!userService.existsUser(userId) || !crawlingService.existsLectureId(lectureId)){
            return null;
        }

        LectureLike lecture = likeService.postLike(lectureId, userId);
        return lecture;

    }

    // 강의 찜하기 해제
    @DeleteMapping("/like/{lectureId}")
    public LectureLike deleteLectureLike(@PathVariable Long lectureId, @RequestHeader(value="Authorization") Long userId) {
        // 검증
        if(!userService.existsUser(userId) || !crawlingService.existsLectureId(lectureId)){
            return null;
        }
        LectureLike lecture = likeService.deleteLike(lectureId, userId);
        return lecture;
    }



    @GetMapping("/ping")
    public String getPing() {
        return "success";
    }
}
