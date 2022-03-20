package cz.upce.fei.cv05.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    String upload(MultipartFile image);
}
