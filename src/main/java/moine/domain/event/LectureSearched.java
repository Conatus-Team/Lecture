package moine.domain.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import moine.domain.dto.history.LikeDto;
import moine.domain.dto.history.SearchDto;
import moine.domain.entity.LectureLike;
import moine.domain.entity.LectureSearch;
import moine.infra.AbstractEvent;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LectureSearched extends AbstractEvent {

//    private Long id;
//    private Long userId;
//    private String keyword;

    private List<SearchDto> searchDtoList = new ArrayList<SearchDto>();

    public LectureSearched(List<LectureSearch> searchList) {
        super(searchList);

        for (LectureSearch element : searchList) {
            SearchDto dto = new SearchDto(
                    element.getId(),
                    element.getUser().getUserId(),
                    element.getKeyword()
            );

            searchDtoList.add(dto);
        }
    }


    public LectureSearched() {
        super();
    }
}
