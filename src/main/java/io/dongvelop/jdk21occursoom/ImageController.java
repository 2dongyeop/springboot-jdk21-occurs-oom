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
     * JDK 17과 달리, 21에서 Out of Memory Error를 유발하는 getBytes() 메서드 사용 예제
     *
     * @param multipartFile : 저장할 파일
     * @return : empty response
     */
    @PostMapping("/videos/danger")
    public ResponseEntity<String> saveVideoDangerCode(final MultipartFile multipartFile) {
        logMultipartFIle(multipartFile);
        final File file = FileUtil.convertToFile(multipartFile);
        log.info("saved file name[{}]", file.getName());

        return ResponseEntity.ok().build();
    }

    /**
     * JDK 21에서 Out of Memory Error를 방지하기 위한 대안 예제
     *
     * @param multipartFile : 저장할 예제
     * @return : empty response
     */
    @PostMapping("/videos/safe")
    public ResponseEntity<String> saveVideoSafeCode(final MultipartFile multipartFile) {
        logMultipartFIle(multipartFile);
        final File file = FileUtil.convertToFileSafeInJDK21(multipartFile);
        log.info("saved file name[{}]", file.getName());

        return ResponseEntity.ok().build();
    }

    /**
     * 파일 로깅 메서드
     *
     * @param multipartFile : 로깅할 파일
     */
    private void logMultipartFIle(MultipartFile multipartFile) {
        log.info("name[{}]", multipartFile.getName());
        log.info("origin name[{}]", multipartFile.getOriginalFilename());
        log.info("size[{}]", multipartFile.getSize());
    }
}
