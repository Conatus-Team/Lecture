package moine.domain.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="lecture_crawling")
public class LectureCrawlingEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lecture_id;

    // BaseTimeEntity 상속으로 자동 저장
    // created_time // "yyyy-MM-dd/HH:mm:ss"
    // updated_time // "yyyy-MM-dd/HH:mm:ss"

    // soft delete
    @Column(nullable = false)
    private boolean is_deleted = Boolean.FALSE; // 디폴트 false

    // 강의 링크
    @Column(nullable = false, length = 100)
    private String lecture_url;

    // 이미지
    @Column(nullable = true, length = 100)
    private String image_path;

    // 사이트명
    @Column(nullable = true, length = 100)
    private String site_name;

    // 강의명
    @Column(nullable = false, length = 100)
    private String lecture_name;

    // 강사명
    @Column(nullable = true, length = 100)
    private String teacher_name;

    // 수강료
    @Column(nullable = true)
    private long price;

    // 강의 소개
    @Column(nullable = true, length = 100)
    private String introduction;

    // 강의 양
    @Column(nullable = true, length = 100)
    private String amount;

    // 커리큘럼럼
    @Column(nullable = true, length = 100)
    private String curriculum;

    // 취미분야
    @Column(nullable = false, length = 100)
    private String category_name;

    // 원본 사이트에서 좋아요수
    @Column(nullable = true)
    private long origin_like_count;

    // 모이네 유저 좋아요수
    @Column(nullable = true)
    private long user_like_count;


}
