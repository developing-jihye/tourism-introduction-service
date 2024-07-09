# 관광지 소개 서비스 🌍

[GSITM 4기] 스터디 4조 tours 의 협업 공간입니다.
Spring Boot, JPA, MyBatis를 사용한 관광지 소개 서비스입니다. 
<br/>
[4조 회의록 바로가기](https://developing-jihye.notion.site/tours-tourism-introduction-service-6f5f0ec06ae84b408acc7f892aaf608b) 📋

## 목차 📑

- [소개](#소개)
- [기능](#기능)
- [기술 스택](#기술-스택)
- [설치](#설치)
- [사용법](#사용법)
- [기여](#기여)

## 소개 🏖️

이 프로젝트는 관광지를 소개하기 위해 설계된 웹 애플리케이션입니다. <br/>
사용자 인증, 장소 리뷰, 카테고리 기반 검색 등의 기능을 포함하고 있습니다.

## 기능 ✨

- 사용자 등록 및 인증 (등록은 안 할 수도) 🙋‍♂️
- 카테고리, 장소 및 리뷰에 대한 CRUD 작업 📝
- 카테고리 및 위치별 장소 검색 🔍
- 역할 기반 접근 제어 🔐

## 기술 스택 🛠️

- **백엔드**: Spring Boot, JPA, MyBatis
- **프론트엔드**: 예: React, Angular 등등 추후 추가 필요
- **데이터베이스**: MySQL, H2 (개발 및 테스트 용도)
- **보안**: Spring Security
- **빌드 도구**: Gradle 
- **테스트**: JUnit

## 설치 ⚙️

1. 레포지토리를 클론합니다:
    ```bash
    git clone https://github.com/your-username/tourism-introduction-service.git
    ```

2. 데이터베이스를 설정합니다:
    - MySQL이 설치되고 실행 중인지 확인합니다.
    - `tourism_db`라는 데이터베이스를 생성합니다.
    - `application.properties` 또는 `application.yml` 파일에 데이터베이스 자격 증명을 업데이트합니다.

3. 프로젝트를 빌드합니다:
    ```bash
    ./gradlew build  # 또는 ./mvnw clean install
    ```

4. 애플리케이션을 실행합니다:
    ```bash
    ./gradlew bootRun  # 또는 ./mvnw spring-boot:run
    ```

## 사용법 🚀

- 애플리케이션에 접속: `http://localhost:8080`
- 제공된 엔드포인트를 사용하여 사용자 등록, 장소 추가, 리뷰 작성 등을 할 수 있습니다.

## 기여 🙌

기여를 환영합니다! 개선 사항이나 버그에 대한 이슈를 제출하거나 풀 리퀘스트를 만들어주세요.

1. 레포지토리를 포크합니다.
2. 기능 브랜치를 생성합니다 (`git checkout -b feature/my-new-feature`).
3. 변경 사항을 커밋합니다 (`git commit -am 'Add some feature'`).
4. 브랜치에 푸시합니다 (`git push origin feature/my-new-feature`).
5. 새 풀 리퀘스트를 만듭니다.

