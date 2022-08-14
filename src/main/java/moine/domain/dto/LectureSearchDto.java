package moine.domain.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureSearchDto {
    private String keyword;
    private long userId;
}
