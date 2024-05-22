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

![direct-buffer-memory.png](result-mov%2Fdirect-buffer-memory.png)

<br/>

## *Summary.*
### JDK 17
MultipartFile.getBytes() 호출 시, 200건의 요청이 정상 처리
![jdk17.gif](result-mov%2Fjdk17.gif)

### JDK 21
MultipartFile.getBytes() 호출 시, Out of Memory가 발생하는 영상
![jdk21.gif](result-mov%2Fjdk21.gif)

<br/>

## *Test Flow.*
모든 실행 결과 및 메모리 분석은 아래 순서로 진행합니다.

1. JDK 버전 선택 후 JVM 애플리케이션 실행
2. VisualVM Monitor 탭에서 그래프 파악
3. jcmd baseline 지정
4. Apache Jmeter로 스레드그룹 요청 실행
5. VisualVM Monitor 탭에서 그래프 비교
6. jcmd Memory Diff 비교
