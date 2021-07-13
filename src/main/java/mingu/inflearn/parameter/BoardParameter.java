package mingu.inflearn.parameter;

import lombok.Data;
import lombok.NoArgsConstructor;
import mingu.inflearn.domain.BoardType;

@Data
@NoArgsConstructor
public class BoardParameter {
    private int boardSeq;
    private BoardType boardType;
    private String title;
    private String contents;

    public BoardParameter(BoardType boardType, String title, String contents) {
        this.boardType = boardType;
        this.title = title;
        this.contents = contents;
    }
}
