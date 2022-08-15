package moine.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity(name="recommend")
public class Recommend {

    // RecommendSystem으로 부터 전달받은 추천 강의 저장
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // soft delete
    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE; // 디폴트 false

    // 해당 강의의 취미분야
    @Column(name="category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name="lecture_id")
    private LectureCrawling lectureCrawling;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
