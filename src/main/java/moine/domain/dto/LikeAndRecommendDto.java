package moine.domain.dto;

import lombok.*;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureLike;
import moine.domain.entity.LectureRecommend;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeAndRecommendDto {
    private List<LectureCrawling> crawlingList = null; // 로그인 전
    private List<LectureLike> likeList = null;
    private List<LectureRecommend> recommendList = null;
}
