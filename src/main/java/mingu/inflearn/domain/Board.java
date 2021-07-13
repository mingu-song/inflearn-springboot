package mingu.inflearn.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int boardSeq;
    private BoardType boardType;
    private String title;
    private String contents;
    private Date regDate;
}
