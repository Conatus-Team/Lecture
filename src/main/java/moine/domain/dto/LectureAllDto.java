package moine.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moine.domain.entity.LectureCrawling;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureAllDto {
    // lectureId, imagePath,lectureName, teacherName, price, categoryName
    private List<LectureCrawling> data;

    // 찜하기 여부
    private List<Long> likeId = null;


}
