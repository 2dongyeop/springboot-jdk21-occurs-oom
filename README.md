# springboot-jdk21-occurs-oom
Out of Memory when Using JDK 21(Multipartfile)

## *Overview.*
JDK 21에서 MultipartFile 클래스의 getBytes() API 호출 시, 발생하는 Out of Memory Error를 재현합니다.

모든 내용 정리는 [노션(첨부링크)](https://www.notion.so/leedongyeop/Out-of-Memory-when-Using-JDK-21-e3dad8ab534247f2922002118803789d#e9d4048dba91439ebe9820578bf9ac3b)에서 확인하실 수 있습니다.

<br/>

## *Quick Start.*

### 1. Git Clone
```bash
# ssh
$ git clone git@github.com:2dongyeop/springboot-jdk21-occurs-oom.git

# https
$ git clone https://github.com/2dongyeop/springboot-jdk21-occurs-oom.git
```

### 2. Open in IDE (IntelliJ ... )
Fix below location (file save location)
```yaml
# src/main/resources/application.yml
spring:
  application:
    name: jdk21-occurs-oom

  servlet:
    multipart:
      enabled: true
      max-request-size: 10MB
      max-file-size: 10MB
      # FIXME : change to path
      location: /Users/2dongyeop/Developments/jdk21-occurs-oom/images
```

### 3. Request With Apache Jmeter
```
Content-Type : multipart/form-data
Request Url : localhost:8080/images
HTTP Method : POST
Using Images : images/sample.jpg (Image size should be less than 10MB) 
```
