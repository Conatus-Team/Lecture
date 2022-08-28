package moine.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name="user")
public class User extends BaseTimeEntity{

    @Id
    // @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;

    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE; // 디폴트 false

    @Column(name="user_name", nullable = false, length = 100)
    private String userName;

    @Column(name="user_nickname",nullable = true, length = 100)
    private String userNickname;

    @Column(name="email",nullable = false, length = 100, unique = true)
    private String email;

//    @Column(name="password",nullable = false, length = 100)
//    private String password;



//    @Builder
//    public User(Long userId, String userNickname){
//        this.userId = userId;
//        this.userNickname = userNickname;
//    }
//
//    @OneToMany(mappedBy = "user")
//    private List<LectureLike> lectureLikes = new ArrayList<>();


}
