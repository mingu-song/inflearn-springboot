package mingu.inflearn.domain;

import lombok.Data;

@Data
public class UploadFile {

    private int uploadFileSeq;
    private String pathname;
    private String filename;
    private String originalFilename;
    private int size;
    private String contentType;
    private String resourcePathname;
}
