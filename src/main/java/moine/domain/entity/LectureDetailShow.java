package moine.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="lecture_detail_show")
public class LectureDetailShow extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // soft delete
    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE; // 디폴트 false

    // FK
    // 여러 강의가 찜하기 될 수 있다.
    @ManyToOne
    @JoinColumn(name="lecture_id")
    private LectureCrawling lectureCrawling;


    // 해당 강의의 취미분야
    @Column(name="category_name")
    private String categoryName;

    // FK
    // 한 명의 user는 여러 강의를 찜하기할 수 있다
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
