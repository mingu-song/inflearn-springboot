package mingu.inflearn.service;

import lombok.RequiredArgsConstructor;
import mingu.inflearn.domain.UploadFile;
import mingu.inflearn.parameter.UploadFileParameter;
import mingu.inflearn.repository.UploadFileRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UploadFileService {
    private final UploadFileRepository repository;

    public void save(UploadFileParameter parameter) {
        repository.save(parameter);
    }

    public UploadFile get(int uploadFileSeq) {
        return repository.get(uploadFileSeq);
    }
}
