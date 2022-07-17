package moine.domain;

import moine.domain.*;
import moine.infra.AbstractEvent;


public class LectureDetailShown extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long lectureId;
    private String category;

    public LectureDetailShown(){
        super();
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
