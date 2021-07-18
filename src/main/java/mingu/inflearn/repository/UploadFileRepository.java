package mingu.inflearn.repository;

import mingu.inflearn.domain.UploadFile;
import mingu.inflearn.parameter.UploadFileParameter;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository {
    void save(UploadFileParameter uploadFileParameter);

    UploadFile get(int uploadFileSeq);
}
