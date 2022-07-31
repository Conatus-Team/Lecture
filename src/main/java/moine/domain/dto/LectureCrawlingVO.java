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
    // 취미분야
    private String categoryName;

    // 강의명
    private String lectureName;

    // 강사명
    private String teacherName;

    // 강의 링크
    private String lectureUrl;

    // 원본 사이트에서 좋아요수
    private long originLikeCount;

    // 사이트명
    private String siteName;

//
//    // 이미지
//    private String imagePath;
//

//
//    // 수강료
//    private long price;
//
//    // 강의 소개
//    private String introduction;
//
//    // 강의 양
//    private String amount;
//
//    // 커리큘럼럼
//    private String curriculum;
//
//    // 모이네 유저 좋아요수
//    private long userLikeCount;

//    public String getLectureUrl() {
//        return "https://www.dongacc.com/online/"+lecture_url;
//    }
}
