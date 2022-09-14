package moine.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendedItemDto {
    Long lectureId;
    Long userId;
}