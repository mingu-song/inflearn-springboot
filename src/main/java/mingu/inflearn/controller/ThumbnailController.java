package mingu.inflearn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.config.exception.BaseException;
import mingu.inflearn.config.http.BaseResponseCode;
import mingu.inflearn.domain.ThumbnailType;
import mingu.inflearn.domain.UploadFile;
import mingu.inflearn.service.UploadFileService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/thumbnail")
public class ThumbnailController {

    private final UploadFileService uploadFileService;

    @GetMapping("/make/{uploadFileSeq}/{thumbnailType}")
    public void make(@PathVariable int uploadFileSeq, @PathVariable ThumbnailType thumbnailType, HttpServletResponse response) {
        UploadFile uploadFile = uploadFileService.get(uploadFileSeq);
        if (uploadFile == null) {
            throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
        }
        String pathname = uploadFile.getPathname();
        File file = new File(pathname);
        if (!file.isFile()) {
            throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
        }
        try {
            String thumbnailPathname = uploadFile.getPathname().replace(".",
                    "_" + thumbnailType.getWidth() + "_" + thumbnailType.getHeight() + ".");
            File thumbnailFile = new File(thumbnailPathname);
            if (!thumbnailFile.isFile()) {
                Thumbnails.of(pathname)
                    .size(thumbnailType.getWidth(), thumbnailType.getHeight())
                    .toFile(thumbnailPathname);
            }
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            FileCopyUtils.copy(new FileInputStream(thumbnailFile), response.getOutputStream());
            log.info("thumbnailFilename : {}", thumbnailPathname);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
