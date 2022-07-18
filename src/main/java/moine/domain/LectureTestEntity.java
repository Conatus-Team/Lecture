package moine.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Getter
@Setter
@Entity(name="lecture_test")
public class LectureTestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 30)
    private String lecturename;

    @Column(nullable = false, length = 100)
    private String category;


}
