package io.dongvelop.jdk21occursoom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 05. 13
 * @description 이미지 저장 요청을 받는 API Endpoint
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final static String SAMPLE_IMAGE_PATH = "samples/smaple.jpg";
    private final static String SAMPLE_VIDEO_PATH = "samples/smaple.mp4";

    /**
     * 파일 저장 요청 API <br/>
     * images/sample.jpg를 요청시 사용. (9.8MB) <br/>
     *
     * @return : 파일이 저장된 경로 + 이름
     */
    @PostMapping("/images")
    public ResponseEntity<String> saveImage(final MultipartFile file) {
        if (file == null || file.isEmpty())
            return ResponseEntity.badRequest().body("""
                    샘플 파일을 확인하세요. 크기가 10MB 이하여야 합니다.
                    """);

        return ResponseEntity.ok(imageService.saveImage(file, FileStorageType.LOCAL));
    }

    @PostMapping("/videos/danger")
    public ResponseEntity<String> saveVideoDangerCode(final MultipartFile multipartFile) {
        validateFileSize(multipartFile);
        File file = FileUtil.convertToFileDangerousInJDK21(multipartFile);

        // TODO : save video logic
        return ResponseEntity.ok().build();
    }

    @PostMapping("/videos/safe")
    public ResponseEntity<String> saveVideoSafeCode(final MultipartFile multipartFile) {
        validateFileSize(multipartFile);
        File file = FileUtil.convertToFileSafeInJDK21(multipartFile);

        // TODO : save video logic
        return ResponseEntity.ok().build();
    }


    private void validateFileSize(final MultipartFile file) {
        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("file size must less than 10MB");
    }


}
