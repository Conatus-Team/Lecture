package moine.infra.controller;

import lombok.RequiredArgsConstructor;
import moine.domain.dto.*;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureDetailShow;
import moine.domain.entity.LectureLike;
import moine.domain.entity.User;
import moine.domain.service.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
//@RequestMapping(value="/lecture")
@RequiredArgsConstructor
@RequestMapping(value="/lecturelist")
public class LectureController {

    private final CrawlingService crawlingService;
    private final SearchService searchService;
    private final UserService userService;
    private final LikeService likeService;
    private final DetailShowService detailShowService;

    // 관리자
    // 크롤링 실행 및 DB 저장
    @GetMapping("/crawlingSave")
    public List<LectureCrawlingVO> LectureCrawlingListSave(HttpServletRequest request){
        List<LectureCrawlingVO> list = crawlingService.saveLectureCrawlingList();
        return list;

    }

    // 사용자
    // 회원가입
    @PostMapping("/signUp")
    public User postUser(@RequestBody SignUpDto signUpDto) {
        System.out.println("signUpDto = " + signUpDto);
        User newUser = userService.postUser(signUpDto.getUserName(), signUpDto.getUserNickname());

        return newUser;
    }

    // 사용자
    // 모든 강의 보기
    @GetMapping("")
    public List<LectureCrawling> getLectureCrawlingList(HttpServletRequest request) {

        // 크롤링 데이터 불러오기
        List<LectureCrawling> list = crawlingService.getLectureCrawlingList();

        return list;

    }

    // 사용자
    // 특정 강의 보기
    @PostMapping("/{id}")
    public LectureDetailShow getLectureItem(@PathVariable Long id, @RequestBody UserIdDto userIdDto){
        // id에 해당하는 강의
//        LectureCrawling lectureItem = crawlingService.getLectureById(id);

        // lecture_detail_show 에 클릭한 강의, 유저를 저장하고 클릭 횟수 증가
        LectureDetailShow lectureDetailShow = detailShowService.saveLectureDetailShow(id, userIdDto.getUserId());

        return lectureDetailShow;

    }



    // 사용자
    // 강의 검색하기
    @PostMapping("/search")
    public List<LectureCrawling> postLectureSearchResult(@RequestBody SearchDto searchDto) {
        String keyword = searchDto.getKeyword();
        Long userId = searchDto.getUserId();

        // 키워드를 포함하는 강의를 DB에서 가져오기
        List<LectureCrawling> results = searchService.search(keyword);

        // lecture_search 디비에 어떤 userid가 어떤 키워드를 검색했는지 저장
        searchService.saveSearch(keyword, userId);

        // 추출된 강의 API 전송
        return results;
    }

    // 강의 찜하기
    @PostMapping("/like")
    public LectureLike postLectureLike(@RequestBody LikeDto likeDto) {
        Long lectureId = likeDto.getLectureId();
        Long userId = likeDto.getUserId();

        LectureLike lecture = likeService.likeResult(lectureId, userId);

        return lecture;

    }

}
