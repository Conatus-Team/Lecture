package moine.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import moine.domain.entity.LectureDetailShow;
import moine.infra.AbstractEvent;

import java.util.List;

@Data
//@AllArgsConstructor
public class LectureDetailShown extends AbstractEvent {

//    private Long id;
//    private Long userId;
//    private Long lectureId;
//    private String category;
//    private Integer clickCount;
    private List<LectureDetailShow> detailShowList;

//    public LectureDetailShown(LectureDetailShow aggregate) {
//        super(aggregate);
//    }
//
    public LectureDetailShown(List<LectureDetailShow> detailShowList) {
        super(detailShowList);
    }

    public LectureDetailShown() {
        super();
    }
}
