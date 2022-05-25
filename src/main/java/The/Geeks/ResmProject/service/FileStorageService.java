package The.Geeks.ResmProject.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import The.Geeks.ResmProject.domain.Image;
import The.Geeks.ResmProject.repo.FileDBRepository;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public Image store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    public Image getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<Image> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
