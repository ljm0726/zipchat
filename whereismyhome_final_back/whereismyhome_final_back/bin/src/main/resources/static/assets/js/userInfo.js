function withdrawal() {
  //회원탈퇴
  //세션 로그아웃
  //회원 정보 삭제
  //메인페이지로 이동

  let id = window.sessionStorage.getItem("user");
  localStorage.removeItem(id);
  window.sessionStorage.clear();
  alert("탈퇴!");
  location.replace("main.jsp");
}
function createUserInfoForm() {
  //유저정보 리스트 삭제
  let userInfo = document.querySelector(".userInfo_div-ul");

  let id = document.getElementById("userInfoId").innerText;
  let password = document.getElementById("userInfoPassword").innerText;
  let name = document.getElementById("userInfoName").innerText;
  let address = document.getElementById("userInfoAddress").innerText;
  let phone_number = document.getElementById("userInfoPhoneNumber").innerText;
  userInfo.innerHTML = "";
  // alert(id + password + name + address + phone_number);
  //form 생성
  let userInfoForm = document.querySelector(".form");
  // alert(userInfoForm);
  let formHtml = `      
        <form name="register-form" class="register-form">
          <input id="id" name="id" type="text" value="${id}" />
          <input id="password" name="password" type="password" value="${password}" />
          <input id="name" name="name" type="text" value="${name}" />
          <input id="address" name="address" type="text" value="${address}" />
          <input id="phone_number" name="phone_number" type="tel" value="${phone_number}" />
          <button class="btn btn-outline-primary userInfoBtn" onclick=userInfoChange() type="button">확인</button>
        </form>`;
  userInfoForm.innerHTML = formHtml;
}
function userInfoChange() {
  //회원정보수정
  //form 생성
  //form값 비어있는지 확인
  //form값 받아오기
  //session 값 수정
  //local 값 수정
  let id = document.getElementById("id").value;
  let password = document.getElementById("password").value;
  let name = document.getElementById("name").value;
  let address = document.getElementById("address").value;
  let phone_number = document.getElementById("phone_number").value;
  // alert(id + " " + password + " " + name + " " + address + " " + phone_number);
  //form값 비어있는지 확인
  if (id == "" || password == "" || name == "" || address == "" || phone_number == "") {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  }
  //form값 받아오기
  id = document.getElementById("id").value;
  password = document.getElementById("password").value;
  name = document.getElementById("name").value;
  address = document.getElementById("address").value;
  phone_number = document.getElementById("phone_number").value;
  //session 값 수정
  let userSession = window.sessionStorage.getItem("user");
  window.sessionStorage.clear();
  window.sessionStorage.setItem("user", id);

  //local 값 수정
  localStorage.removeItem(userSession);
  const user = {
    id: id,
    password: password,
    name: name,
    address: address,
    phone_number: phone_number,
  };
  localStorage.setItem(user.id, JSON.stringify(user));
  alert("수정 성공");
  location.replace("userInfo.jsp");
}
