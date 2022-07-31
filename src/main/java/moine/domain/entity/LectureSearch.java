package moine.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="lecture_search")
public class LectureSearch extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // soft delete
    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE; // 디폴트 false

//    // 사용자 구분
//    @Column(name="user_id", nullable = false)
//    private long userId;

    // 키워드
    @Column(nullable = false, length = 100)
    private String keyword;

    // 한 명의 user는 여러 keyword를 가질 수 있다
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
