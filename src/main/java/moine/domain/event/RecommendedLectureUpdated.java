package moine.domain.event;

import lombok.Data;
import moine.infra.AbstractEvent;

@Data
public class RecommendedLectureUpdated extends AbstractEvent {

    private Long id;

//    public RecommendedLectureUpdated(){
//        super();
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
}
