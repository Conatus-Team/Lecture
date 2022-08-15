package moine.domain.dto.history;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {
    private Long id;
    private Long userId;
    private Long lectureId;
    private String category;
}
