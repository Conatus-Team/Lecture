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
    private String category_name;

    // 강의명
    private String lecture_name;

    // 강사명
    private String teacher_name;

    // 강의 링크
    private String lecture_url;

    // 원본 사이트에서 좋아요수
    private long origin_like_count;

    // 사이트명
    private String site_name;

//
//    // 이미지
//    private String image_path;
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
//    private long user_like_count;

//    public String getLectureUrl() {
//        return "https://www.dongacc.com/online/"+lecture_url;
//    }
}
