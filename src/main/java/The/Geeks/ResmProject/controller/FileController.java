package The.Geeks.ResmProject.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import The.Geeks.ResmProject.domain.Image;
import The.Geeks.ResmProject.message.ResponseFile;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.repo.FileDBRepository;
import The.Geeks.ResmProject.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;

@Controller
@CrossOrigin("http://localhost:8090")
@Slf4j
public class FileController {

  @Autowired
  private FileStorageService storageService;
  @Autowired
  FileDBRepository  fileDBRepository;

  // @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.store(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/files/")
          .path(dbFile.getId())
          .toUriString();

      return new ResponseFile(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    Image fileDB = storageService.getFile(id);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }
// new version with list of images files


  @PostMapping("/upload")
  public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Path path = Paths.get(  fileName);
    try {
      Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }


    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/files/download/")
        .path(fileName)
        .toUriString();
    return ResponseEntity.ok(fileDownloadUri);
  }
  @PostMapping("/multi-upload")
  public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
    List<Object> fileDownloadUrls = new ArrayList<>();
    Arrays.asList(files)
        .stream()
        .forEach(file -> fileDownloadUrls.add(uploadFile(file).getBody()));
    return ResponseEntity.ok(fileDownloadUrls);
  }

  @PostMapping("/upload-extra-param")
  public ResponseEntity uploadWithExtraParams(@RequestParam("file") MultipartFile file,
      @RequestParam String extraParam) {
    log.info("Extra param " + extraParam);
    return uploadToLocalFileSystem(file);
  }

  @PostMapping("/upload/db")
  public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {
    Image doc = new Image();
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    doc.setName(fileName);
    try {
      doc.setData(file.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
    fileDBRepository.save(doc);
    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/files/download/")
        .path(fileName).path("/db")
        .toUriString();
    return ResponseEntity.ok(fileDownloadUri);
  }

  // @GetMapping("/download/{fileName:.+}")
  // public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
  //   Path path = Paths.get(fileBasePath + fileName);
  //   Resource resource = null;
  //   try {
  //     resource = new UrlResource(path.toUri());
  //   } catch (MalformedURLException e) {
  //     e.printStackTrace();
  //   }
  //   return ResponseEntity.ok()
  //       .contentType(MediaType.parseMediaType(contentType))
  //       .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
  //       .body(resource);
  // }

  // @GetMapping("/download/{fileName:.+}/db")
  // public ResponseEntity downloadFromDB(@PathVariable String fileName) {
  //   Image document = fileDBRepository.findByName(fileName);
  //   return ResponseEntity.ok()
  //       .contentType(MediaType.parseMediaType(contentType))
  //       .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
  //       .body(document.getData());
  // }

  // @GetMapping(value = "/zip-download", produces = "application/zip")
  // public void zipDownload(@RequestParam List<String> name, HttpServletResponse response) throws IOException {
  //   ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
  //   for (String fileName : name) {
  //     FileSystemResource resource = new FileSystemResource(fileBasePath + fileName);
  //     ZipEntry zipEntry = new ZipEntry(resource.getFilename());
  //     zipEntry.setSize(resource.contentLength());
  //     zipOut.putNextEntry(zipEntry);
  //     StreamUtils.copy(resource.getInputStream(), zipOut);
  //     zipOut.closeEntry();
  //   }
  //   zipOut.finish();
  //   zipOut.close();
  //   response.setStatus(HttpServletResponse.SC_OK);
  //   response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipFileName + "\"");
  // }
}
