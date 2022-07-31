package moine.domain.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name="lecture_crawling")
public class LectureCrawling extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lecture_id")
    private long lectureId;

    // BaseTimeEntity 상속으로 자동 저장
    // created_time // "yyyy-MM-dd/HH:mm:ss"
    // updated_time // "yyyy-MM-dd/HH:mm:ss"

    // soft delete
    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE; // 디폴트 false

    // 강의 링크
    @Column(name="lecture_url", nullable = false, length = 100)
    private String lectureUrl;

    // 이미지
    @Column(name="image_path", nullable = true, length = 100)
    private String imagePath;

    // 사이트명
    @Column(name="site_name", nullable = true, length = 100)
    private String siteName;

    // 강의명
    @Column(name="lecture_name", nullable = false, length = 100)
    private String lectureName;

    // 강사명
    @Column(name="teacher_name", nullable = true, length = 100)
    private String teacherName;

    // 수강료
    @Column(nullable = true)
    private long price;

    // 강의 소개
    @Column(nullable = true, length = 100)
    private String introduction;

    // 강의 양
    @Column(nullable = true, length = 100)
    private String amount;

    // 커리큘럼
    @Column(nullable = true, length = 100)
    private String curriculum;

    // 취미분야
    @Column(name="category_name", nullable = false, length = 100)
    private String categoryName;

    // 원본 사이트에서 좋아요수
    @Column(name="origin_like_count", nullable = true)
    private long originLikeCount;

    // 모이네 유저 좋아요수
    @Column(name="user_like_count", nullable = true)
    private long userLikeCount;

//    @OneToMany(mappedBy = "lectureCrawling")
//    private List<LectureLike> lectureLikes = new ArrayList<>();

}
