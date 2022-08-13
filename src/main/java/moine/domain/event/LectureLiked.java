package moine.domain.event;

import lombok.Data;
import moine.infra.AbstractEvent;

@Data
public class LectureLiked extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long lectureId;
    private String category;

    public LectureLiked() {
        super();
    }

}
