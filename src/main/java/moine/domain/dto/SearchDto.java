package moine.domain.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {
    private String keyword;
    private long userId;
}
