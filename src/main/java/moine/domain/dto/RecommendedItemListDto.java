package moine.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecommendedItemListDto {
    List<RecommendedItemDto> data;
}
