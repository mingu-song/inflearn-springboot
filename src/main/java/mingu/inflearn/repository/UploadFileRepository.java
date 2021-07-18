package mingu.inflearn.repository;

import mingu.inflearn.parameter.UploadFileParameter;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository {
    void save(UploadFileParameter uploadFileParameter);
}
