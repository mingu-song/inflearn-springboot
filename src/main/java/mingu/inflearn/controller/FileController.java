package mingu.inflearn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.config.GlobalConfig;
import mingu.inflearn.config.exception.BaseException;
import mingu.inflearn.config.http.BaseResponse;
import mingu.inflearn.config.http.BaseResponseCode;
import mingu.inflearn.parameter.UploadFileParameter;
import mingu.inflearn.service.UploadFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

    private final GlobalConfig globalConfig;

    private final UploadFileService uploadFileService;

    @PostMapping("/save")
    @ApiOperation(value = "업로드", notes = "")
    public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {
        log.info("multipartFile : {}", multipartFile);
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL);
        }

        // 날짜폴더추가
        String currDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String uploadFilePath = globalConfig.getUploadFilePath() + currDate + "/";
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
        String resourcePathname = globalConfig.getUploadResourcePath() + currDate + "/" + filename;
        File dest = new File(pathName);
        log.info("dest : {}", dest);

        try {
            multipartFile.transferTo(dest);
            // 파일업로드 후 DB 저장
            UploadFileParameter parameter = new UploadFileParameter();
            parameter.setContentType(multipartFile.getContentType());
            parameter.setOriginalFilename(multipartFile.getOriginalFilename());
            parameter.setFilename(filename);
            parameter.setPathname(pathName);
            parameter.setSize((int)multipartFile.getSize());
            // static resource 접근경로
            parameter.setResourcePathname(resourcePathname);
            uploadFileService.save(parameter);
        } catch (IllegalStateException  | IOException e) {
            log.error(e.getMessage(), e);
        }
        return new BaseResponse<>(true);
    }
}
