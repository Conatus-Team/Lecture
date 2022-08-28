package moine.domain.dto.history;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {
    private Long id;
    private Long userId;
    private String keyword;
}
