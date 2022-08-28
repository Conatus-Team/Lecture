package moine.domain.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import moine.domain.dto.history.DetailShowDto;
import moine.domain.dto.history.LikeDto;
import moine.domain.entity.LectureDetailShow;
import moine.domain.entity.LectureLike;
import moine.infra.AbstractEvent;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LectureLiked extends AbstractEvent {

//    private Long id;
//    private Long userId;
//    private Long lectureId;
//    private String category;
    private List<LikeDto> likeDtoList = new ArrayList<LikeDto>();

    public LectureLiked(List<LectureLike> likeList) {
        super(likeList);

        for (LectureLike element : likeList) {
            LikeDto dto = new LikeDto(
                    element.getId(),
                    element.getUser().getUserId(),
                    element.getLectureCrawling().getLectureId(),
                    element.getCategoryName()
            );

            likeDtoList.add(dto);
        }
    }


    public LectureLiked() {
        super();
    }

}
