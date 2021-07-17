package mingu.inflearn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.config.GlobalConfig;
import mingu.inflearn.config.exception.BaseException;
import mingu.inflearn.config.http.BaseResponse;
import mingu.inflearn.config.http.BaseResponseCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

    private final GlobalConfig globalConfig;

    @PostMapping("/save")
    @ApiOperation(value = "업로드", notes = "")
    public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {
        log.info("multipartFile : {}", multipartFile);
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL);
        }

        String uploadFilePath = globalConfig.getUploadFilePath();
        log.info("uploadFilePath : {}", uploadFilePath);

        String ext = Objects.requireNonNull(multipartFile.getOriginalFilename())
                .substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1,
                        multipartFile.getOriginalFilename().length());
        String filename = UUID.randomUUID() + "." + ext;
        log.info("filename : {}", filename);

        File folder = new File(uploadFilePath);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String pathName = uploadFilePath + filename;
        File dest = new File(pathName);
        log.info("dest : {}", dest);

        try {
            multipartFile.transferTo(dest);
        } catch (IllegalStateException  | IOException e) {
            log.error(e.getMessage(), e);
        }
        return new BaseResponse<>(true);
    }
}
