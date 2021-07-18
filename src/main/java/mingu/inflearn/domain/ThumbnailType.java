package mingu.inflearn.domain;

import lombok.Getter;

@Getter
public enum ThumbnailType {
    WEB_MAIN(500, 300),
    WEB_SUB(150, 70),
    ;

    private final int width;
    private final int height;

    ThumbnailType(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
