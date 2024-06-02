# springboot-jdk21-occurs-oom

## *Overview.*
>JDK 21를 사용할 경우, `MultipartFile.getBytes()` 메서드 호출시 아래 사진과 같이
Direct Buffer Memory가 부족하여 `Out of Memory Error`가 발생하는 경우가 있습니다.
>
>*다만, 동일한 소스코드로 JDK 17을 사용할 경우에는 발생하지 않습니다.*
> 
>따라서 두 JDK 버전 간의 Direct Buffer Memory 사용량을 비교하는 글 입니다. 
> 
>자세한 내용은 [노션](https://www.notion.so/leedongyeop/Out-of-Memory-when-Using-JDK-21-e3dad8ab534247f2922002118803789d#e9d4048dba91439ebe9820578bf9ac3b)에서 확인하실 수 있습니다.

![direct-buffer-memory.png](movies%2Fdirect-buffer-memory.png)
- [junhyunny 님의 블로그 글](https://junhyunny.github.io/java/jvm/spring-boot/get-bytes-method-of-multipart-file-in-java21-cause-oome/)을 참고했으며, 원본 글로부터 아래 내용을 첨언하였습니다.
    - [Apache Jmeter 사용 방법](https://www.notion.so/Out-of-Memory-when-Using-JDK-21-e3dad8ab534247f2922002118803789d?pvs=21)
    - [VisualVM 사용 방법](https://www.notion.so/Out-of-Memory-when-Using-JDK-21-e3dad8ab534247f2922002118803789d?pvs=21)
    - [jcmd 사용 방법](https://www.notion.so/Out-of-Memory-when-Using-JDK-21-e3dad8ab534247f2922002118803789d?pvs=21)
    - [Spring Boot 실행 시, JVM Option 설명](https://www.notion.so/Out-of-Memory-when-Using-JDK-21-e3dad8ab534247f2922002118803789d?pvs=21)
    - InputStream 디폴트 버퍼 크기 변경으로 인한 내용 최신화 
      - (8192Byte → 16384Byte)

<br/>

## *Summary.*
### JDK 17 : Out of Memory Error 발생하지 않음
![jdk17-new.gif](movies%2Fjdk17-new.gif)

### JDK 21 : Out of Memory Error 발생
![jdk21-new.gif](movies%2Fjdk21-new.gif)
