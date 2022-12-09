# ssafy-zipchat 🏙
### SSAFY 8기 1학기 최종 관통프로젝트

<br>

## **개요** ✅
- **국토 교통부 아파트 매매 실거래 자료**를 통해 쉽게 시세를 알 수 있게 해주고 <u>주변 상권 및 시설 위치</u>를 함께 제공, <u>관심 지역</u>을 설정하여 관심 지역의 정보를 추가 제공

<br>

## 프로젝트 기간 📆
- **2022년 11월 16일 ~ 2022년 11월 24일**

<br>

## 기술 스택 🛠
<p>
  <img src="https://img.shields.io/badge/Language-Java-007396?style=flat&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/Language-JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=white">
  <img src="https://img.shields.io/badge/Database-MySql-4479A1?style=flat&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/Framework-Vue-4FC08D?style=flat&logo=Vue.js&logoColor=white">
  <img src="https://img.shields.io/badge/Framework-SpringBoot-6DB33F?style=flat&logo=Spring Boot&logoColor=white">
  <img src="https://img.shields.io/badge/Framework-Mybatis-BE3939?style=flat">
  <img src="https://img.shields.io/badge/Library-BootstrapVue-7952B3?style=flat&logo=bootstrap&logoColor=white">
  <img src="https://img.shields.io/badge/API-Kakao_Map-red?style=flat">
  <img src="https://img.shields.io/badge/API-Naver_News-6DB33F?style=flat"> 
  <img src="https://img.shields.io/badge/API-Openweathermap-40AEF0?style=flat">
</p>

<br>

## 팀원 💻
<table>
  <thead>
    <tr>
      <th>이름</th>
      <th>구현 기능</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td rowspan="2">이종민</td>
      <td>Back End - 전반</td>
    </tr>
    <tr>
      <td>Front End - Vuex(회원관리, 게시판, 뉴스API, 관심지역, 날씨 API), 차트, 슬라이드</td>
    </tr>
    <tr>
      <td rowspan="2">최희경</td>
      <td>Back End - 아파트 매매 정보, 버스정류장, 학교 데이터 DB 설계 및 REST 구현</td>
    </tr>
    <tr>
      <td>Front End - 전반</td>
    </tr>
  </tbody>
</table>

<br>

## 기능
![기능](./assets/기능.PNG)

<br>

## 실행 화면

### **🔗메인 페이지**
![메인페이지](./assets/메인페이지.gif)
 ⭕ 메인 화면, 부동산 관려 뉴스 및 공지사항, 관심지역 매물 정보인 3개의 화면으로 메인 페이지 구성
<br>
<br>
<br>

---
### **🔗 회원 정보 관련 페이지**
![회원가입_로그인](./assets/회원가입_로그인.gif)
 ⭕ 회원가입 시 아이디 중복여부 체크, 비밀번호 확인을 통해 추가로 체크
<br>
<br>
![회원정보_확인_수정](./assets/회원정보_확인_수정.gif)
 ⭕ 회원정보 확인 및 수정, 탈퇴 
<br>
<br>
<br>

---
### **🔗 공지사항 페이지**
![공지사항_페이징_검색](./assets/공지사항_페이징_검색.gif)
 ⭕ 공지사항 페이징 구현, 키워드를 통해 제목, 작성자로 검색 가능
<br>
<br>
![공지사항_CRUD](./assets/공지사항_CRUD.gif)
 ⭕ 관리자만 고지사항 쓰기, 수정, 삭제 가능
<br>
<br>
<br>

---
### **🔗 아파트검색 페이지**
 ⭕ 아파트 검색 페이지
 
 ⭕ 아파트 검색 페이지 + 주변 상권 및 시설 마커
 
 ⭕ 아파트 검색 페이지 + 가장 가까운 버스정류장, 학교를 모달을 통해 보여줌
<br>
<br>
<br>


---
### **🔗 관심지역 페이지**
![관심지역페이지](./assets/관심지역페이지.gif)
 ⭕ 관심지역으로 추가된 지역의 매매 정보 리스트
     관심지역의 뉴스 및 연도별 거래 횟수 차트
<br>
<br>
![메인페이지_슬라이드](./assets/메인페이지_슬라이드.gif)
 ⭕ 메인 페이지에서 관심지역으로 추가된 지역의 최신 거래내역 3개를 보여줌
<br>
