package mingu.inflearn.parameter;

import lombok.Data;
import lombok.NoArgsConstructor;
import mingu.inflearn.domain.BoardType;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardSearchParameter {
    private String keyword;
    private List<BoardType> boardTypes;
}
