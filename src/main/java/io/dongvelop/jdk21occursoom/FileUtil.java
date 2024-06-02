package io.dongvelop.jdk21occursoom;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 05. 15
 * @description 파일 관련 작업 유틸 클래스
 */
@Slf4j
@UtilityClass
public class FileUtil {

    /**
     * MultipartFile을 File 타입으로 변환하는 유틸 메서드 <br/>
     * JDK 17과 달리 JDK 21 사용 시, MultipartFile.getBytes()호출 시 <br/>
     * InputStream이 아닌 ChannelInputStream을 사용함으로써 Out of Memory Error 발생
     *
     * @param multipartFile : 변환할 MultipartFile
     * @return : 변환된 File
     */
    public File convertToFile(final MultipartFile multipartFile) {
        final File convertedFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        try  {
            byte[] bytes = multipartFile.transferTo();getBytes();
            log.info("bytes length[{}]", bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return convertedFile;
    }

    /**
     * MultipartFile을 File 타입으로 변환하는 유틸 메서드 <br/>
     * JDK 21 사용 시 안전한 코드
     *
     * @param multipartFile : 변환할 MultipartFile
     * @return : 변환된 File
     */
    public File convertToFileSafeInJDK21(final MultipartFile multipartFile) {
        final File copiedFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        try (
                final InputStream is = multipartFile.getInputStream();
                final OutputStream os = new FileOutputStream(copiedFile)
        ) {
            final byte[] buffer = new byte[10240];
            int read;
            while ((read = is.read(buffer)) > 0) {
                os.write(buffer, 0, read);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return copiedFile;
    }
}
