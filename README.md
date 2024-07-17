# 안드로이드 스튜디오를 이용한 분실물 등록/조회 애플리케이션

### 📖 프로젝트 개요
이 저장소는 안드로이드 스튜디오를 이용해 분실물을 등록하고 검색할 수 있는 모바일 애플리케이션 시스템의 코드를 포함하고 있습니다. 이 시스템은 사용자 등록, 로그인, 찾아요/발견했어요 게시판, 글쓰기 페이지, 마이페이지와 같은 기능을 제공하여 분실물 발견시 신고자와 발견자 간에 빠른 연락 수단 제공합니다.
<br/><br/>

### 🚀 프로젝트 목표
- 분실물 검색의 용이성 증대
- 분실물 관리의 개선
- 시간과 에너지 절감
- 사용자 만족도 향상
<br/><br/><br/>

## 📝 프로젝트 설명
대학교 내에서 분실물 발생 시 유동인구가 많은 학교 특성 상 습득한 사람/분실한 사람을 찾는것에 어려움을 느끼고 분실물을 제보하고 , 물품을 찾는 글만을 모아 볼 수 있는 커뮤니티 앱을 개발
<br/><br/>

### 💼 애플리케이션의 기능
1. 사용자 등록 및 로그인
2. 메인페이지
3. 찾아요/발견했어요 게시판
4. 글쓰기 페이지
5. 마이페이지
<br/><br/>

### 🗺 메뉴 구조도
<img width=100%, height=400px, src="https://github.com/21-bomi/Android-Studio/blob/main/app/src/main/res/drawable/menu.png"><br/><br/>


### 💻 기술 스택
- **OS:** &nbsp;&nbsp;![Windows 11](https://img.shields.io/badge/Windows%2011-%230079d5.svg?style=for-the-badge&logo=Windows%2011&logoColor=white)
- **프레임워크:** &nbsp;&nbsp;![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
- **언어:** &nbsp;&nbsp;![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
- **툴:** &nbsp;&nbsp;![Nox Player](https://img.shields.io/badge/Nox%20Player-3DDC84?style=for-the-badge&logo=nox&logoColor=white)
- **데이터베이스:** &nbsp;&nbsp;![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white)



<br/><br/>

### ✨ 주요 기능 및 이미지
📌 Home<br/>
<img width=100% src=https://github.com/21-bomi/Android-Studio/blob/main/app/src/main/res/drawable/main.png><br/><br/>
- **메뉴바:** 게시팜 및 페이지를 이용할 수 있는 메뉴 바 구현<br/>
- **버튼:** 버튼을 눌러 원하는 게시판에 들어갈 수 있도록 구현<br/>

📌 Login<br/>
<div style="display: flex; justify-content: space-between;">
  <img width=100% src="https://github.com/21-bomi/Android-Studio/blob/main/app/src/main/res/drawable/login.png">
</div><br/><br/>

- **회원가입:** 이름,ID,PW,이메일을 입력하여 회원가입을 진행(중복 ID 가입 X)<br/>
- **로그인:** 사용자 로그인 기능 구현<br/><br/><br/>

📌 Look for<br/>
<div style="display: flex; justify-content: space-between;">
  <img width=100% src="https://github.com/21-bomi/Android-Studio/blob/main/app/src/main/res/drawable/find.png">
</div><br/><br/>

- **찾아요 게시판:** 찾고자하는 분실물을 조회 <br/>
- **글쓰기 페이지:** 분실물의 위치, 장소, 내용에 대해 상세히 적을 수 있도록 구현<br/>


📌Find<br/>
<div style="display: flex; justify-content: space-between;">
  <img width=100% src="https://github.com/21-bomi/Android-Studio/blob/main/app/src/main/res/drawable/find2.png">
</div><br/>

- **발견했어요 게시판:** 발견한 분실물을 조회<br/>
- **글쓰기 페이지:** 분실물의 위치, 장소, 내용, 사진 첨부 등 분실물에 대해 상세히 적을 수 있도록 구현<br/><br/><br/>

📌 Mypage<br/>
<div style="display: flex; justify-content: space-between;">
  <img width=100% src="https://github.com/21-bomi/Android-Studio/blob/main/app/src/main/res/drawable/find2.png">
</div><br/>

- **마이페이지:** 사용자의 정보 조회 및 아이디 수정<br/><br/><br/>

### 🎧 시연영상</a>
</br></br>

## ⚙️ 프로젝트 설치 및 실행 방법

### 📝 Prerequisites
- Android Studio
- Nox Player
- MySQL 서버

### 📦 설치 방법
1. 저장소를 클론합니다:
    ```sh
    git clone https://github.com/yourusername/Plzfind.git
    cd Plzfind
    ```

2. Android Studio에서 프로젝트를 엽니다.

3. 필요한 Android 라이브러리를 설치합니다.

4. MySQL 데이터베이스를 설정합니다:
   - `bufind`라는 데이터베이스를 생성합니다.
   - 데이터베이스 스키마를 설정하고 DatabaseConfig.java 파일의 MySQL 자격 증명 정보를 업데이트합니다.

### 실행

Android Studio에서 앱을 실행합니다:
Run 버튼을 클릭하여 에뮬레이터(Nox Player) 또는 실제 장치에서 앱을 실행합니다.
