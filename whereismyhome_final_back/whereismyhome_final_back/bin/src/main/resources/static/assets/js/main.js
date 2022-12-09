window.onload = function () {
  //로그인 상태 확인

  let id = window.sessionStorage.getItem("user");
  if (id == null) {
    //logout
    //로그인 회원가입
    let login = document.getElementsByClassName("login");
    for (let i = 0; i < login.length; ++i) login[i].style.display = "none";
    let logout = document.getElementsByClassName("logout");
    for (let i = 0; i < login.length; ++i) logout[i].style.display = "block";
  } else {
    //login
    //로그아웃 회원정보
    let login = document.getElementsByClassName("login");
    for (let i = 0; i < login.length; ++i) login[i].style.display = "block";
    let logout = document.getElementsByClassName("logout");
    for (let i = 0; i < login.length; ++i) logout[i].style.display = "none";
  }

  let yearEl = document.querySelector("#year");
  let yearOpt = `<option value="">매매년도선택</option>`;
  let year = date.getFullYear();
  for (let i = year; i > year - 20; i--) {
    yearOpt += `<option value="${i}">${i}년</option>`;
  }
  yearEl.innerHTML = yearOpt;

  // 브라우저가 열리면 시도정보 얻기.
  sendRequest("sido", "*00000000");
};

function logout() {
  window.sessionStorage.clear();
  location.replace("main.jsp");
}
