package moine.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class History extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = Boolean.FALSE;
    private Long userId;
    private Long LectureId = (long) 0;
    private Integer count = 1;
    private String keyword = "";
    private String category;
    private Boolean isLiked = Boolean.FALSE;
    private Boolean isClicked = Boolean.FALSE;

    @Builder
    public History(Long userId, Long LectureId, String keyword, String category, Boolean isLiked, Boolean isClicked){
        this.userId = userId;
        this.LectureId = LectureId;
        this.keyword = keyword;
        this.category = category;
        this.isLiked = isLiked;
        this.isClicked = isClicked;
    }

    public void updateCount(){
        this.count += 1;
    }
}