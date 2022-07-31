package moine.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name="user")
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // soft delete
    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE; // 디폴트 false

//    // 사용자 구분
//    @Column(name="user_login_id", nullable = false, unique = true)
//    private long userLoginId;

    // 이름
    @Column(name="user_name",nullable = true, length = 100)
    private String userName;

    // 별명
    @Column(name="user_nickname", nullable = true, length = 100)
    private String userNickname;


//
//    @OneToMany(mappedBy = "user")
//    private List<LectureLike> lectureLikes = new ArrayList<>();

}
