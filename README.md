# ssafy-zipchat 🏙
### SSAFY 8기 1학기 최종 관통프로젝트
### 우수상 수상 🥈

<br>

## **개요** ✅
- **국토 교통부 아파트 매매 실거래 자료**를 통해 쉽게 시세를 알 수 있게 해주고 **주변 상권 및 시설 위치**를 함께 제공, **관심 지역**을 설정하여 관심 지역의 정보를 추가 제공
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
### **🔗 지도 (아파트 검색 페이지)**
![지도_지역선택](./assets/지도_지역선택.gif)
 ⭕ 드롭다운을 선택해 원하는 지역의 아파트 실거래가 조회
 ⭕ 드롭다운 뿐만 아니라 드래그로 지도를 움직일 때에도 마커가 갱신된다
 
 ![지도_관심지역](./assets/지도_관심지역.gif)
 ⭕ '관심지역등록' 버튼을 클릭해 관심지역으로 추가할 수 있다
 ⭕ 관심지역으로 등록된 지역은 '관심지역해제' 버튼으로 바뀌며, 이 버튼으로 관심지역을 삭제할 수 있다
 
 ![지도_버스정류장_학교_공공시설](./assets/지도_버스정류장_학교_공공시설.gif)
 ⭕ 지도 좌표의 일정 거리 내에 있는 버스정류장, 학교, 카페, 약국, 은행, 편의점을 지도에 마커로 띄운다
 ⭕ 버스정류장, 학교는 DB기반의 데이터이다
 ⭕ 학교, 카페, 약국, 은행, 편의점은 카카오 API가 제공하는 데이터를 사용하였다

 ![지도_모달](./assets/지도_모달.gif)
 ⭕ 아파트 마커를 클릭하면 우측에 모달 창이 나타난다
 ⭕ UX를 고려하여, 지도의 빈 공간을 클릭하면 모달 창이 사라지고, 맵을 드래그 하는 경우에는 사라지지 않게 구현
 
 ![지도_날씨](./assets/지도_날씨.gif)
 ⭕ 지도를 움직일 때마다 중심 좌표의 실시간 날씨를 갱신한다.
 
<br>
<br>
<br>


---
### **🔗 관심지역 페이지**
![관심지역페이지](./assets/관심지역페이지.gif)
 ⭕ 관심지역으로 추가된 지역의 매매 정보 리스트 <br/>
 &nbsp; &nbsp; &nbsp; 관심지역의 뉴스 및 연도별 거래 횟수 차트
<br>
<br>
![메인페이지_슬라이드](./assets/메인페이지_슬라이드.gif)
 ⭕ 메인 페이지에서 관심지역으로 추가된 지역의 최신 거래내역 3개를 보여줌
<br>
