package io.dongvelop.jdk21occursoom;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 05. 13
 * @description 이미지 작업 Service
 */
@Slf4j
@Service
public class ImageService {

    @Value("${spring.servlet.multipart.location}")
    private String LOCAL_STORAGE_PATH;

    /**
     * 파일 저장 메서드
     *
     * @param file            : 저장할 파일
     * @param fileStorageType : 파일을 저장할 저장소 구분 (S3, LOCAL)
     * @return : 저장된 경로 + 이름
     */
    public String saveImage(final MultipartFile file, final FileStorageType fileStorageType) {

        log.info("fileStorageType[{}]", fileStorageType);

        final String fileName = UUID.randomUUID().toString();
        final String filePath = LocalDateTime.now().toLocalDate().toString();
        log.info("filename[{}], filepath[{}]", fileName, filePath);

        return switch (fileStorageType) {
            case S3 -> saveToS3(filePath, fileName, file);
            case LOCAL -> saveToLocal(filePath, fileName, file);
        };
    }

    /**
     * 로컬에 파일을 저장하는 메서드
     *
     * @param filePath : 저장할 경로
     * @param fileName : 저장할 이름
     * @param file     : 저장할 파일
     * @return : 저장된 경로 + 이름
     */
    private String saveToLocal(final String filePath, final String fileName, final MultipartFile file) {

        final String fileUrl = filePath + File.separator + fileName;
        final File archiveFile = new File(LOCAL_STORAGE_PATH + File.separator + fileUrl);

        try {
            // 디렉토리(날짜-시간꼴)가 존재하지 않으면 생성
            if (!archiveFile.getParentFile().exists()) {
                archiveFile.getParentFile().mkdirs();
            }
            // 파일 저장
            file.transferTo(archiveFile);
        } catch (IOException e) {
            log.error("filePath[{}], fileName[{}] fail to save", filePath, fileName, e);
        }

        return fileUrl;
    }

    private String saveToS3(final String filePath, final String fileName, final MultipartFile file) {
        return Strings.EMPTY;
    }
}
