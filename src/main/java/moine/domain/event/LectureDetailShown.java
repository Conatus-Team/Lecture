package moine.domain.event;

import lombok.Getter;
import lombok.Setter;
import moine.domain.dto.history.DetailShowDto;
import moine.domain.entity.LectureDetailShow;
import moine.infra.AbstractEvent;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
public class LectureDetailShown extends AbstractEvent {
//
//    private Long id;
//    private Long userId;
//    private Long lectureId;
//    private String category;
//    private Integer clickCount;
//    private List<LectureDetailShow> detailShowList;
    private List<DetailShowDto> detailShowDtoList = new ArrayList<DetailShowDto>();

//    public LectureDetailShown(LectureDetailShow aggregate) {
//        super(aggregate);
//    }

    public LectureDetailShown(List<LectureDetailShow> detailShowList) {
        super(detailShowList);
        System.out.println("detailShowList.size() = " + detailShowList.size());
        for (LectureDetailShow element: detailShowList) {
            DetailShowDto dto = new DetailShowDto(
                    element.getId(),
                    element.getUser().getUserId(),
                    element.getLectureCrawling().getLectureId(),
                    element.getCategoryName(),
                    element.getClickCount()
            );

            System.out.println("dto = " + dto);
            detailShowDtoList.add(dto);
        }

    }



    public LectureDetailShown() {
        super();
    }
}
