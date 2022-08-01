package moine.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureCrawlingVO {
    // fields[0]: 취미분야
    private String categoryName;

    // 1: 강의명
    private String lectureName;

    // 2: 강사명
    private String teacherName;

    // 3: 강의 링크
    private String lectureUrl;

    // 4: 원본 사이트에서 좋아요수
    private Integer originLikeCount;

    // 5: 사이트명
    private String siteName;


    // 6: 이미지 경로
    private String imagePath;

    // 7: 강의 소개
    private String introduction;

    // 8: 커리큘럼
    private String curriculum;

    // 9: 수강료
    private  String price;
//    private long price;



    // 10 : 강의 시간
    private String amount;


//
//    // 모이네 유저 좋아요수
//    private Integer userLikeCount;

//    public String getLectureUrl() {
//        return "https://www.dongacc.com/online/"+lecture_url;
//    }
}
