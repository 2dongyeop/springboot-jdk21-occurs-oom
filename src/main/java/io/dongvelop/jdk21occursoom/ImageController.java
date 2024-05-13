package io.dongvelop.jdk21occursoom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 05. 13
 * @description 이미지 저장 요청을 받는 API Endpoint
 */
@Slf4j
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final static String SAMPLE_IMAGE_PATH = "images/smaple.jpg";

    /**
     * 파일 저장 요청 API <br/>
     * images/sample.jpg를 요청시 사용. (9.8MB) <br/>
     *
     * @return : 파일이 저장된 경로 + 이름
     */
    @PostMapping
    public ResponseEntity<String> saveImage(final MultipartFile file) {
        if (file == null || file.isEmpty())
            return ResponseEntity.badRequest().body("""
                    샘플 파일을 확인하세요. 크기가 10MB 이하여야 합니다.
                    """);

        return ResponseEntity.ok(imageService.saveImage(file, FileStorageType.LOCAL));
    }
}
