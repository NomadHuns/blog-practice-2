<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="container my-3">
            <div class="container">
                <form action="/join" method="post" onsubmit="return valid()">
                    <div class="form-group mb-2 ">
                        <input type="text" name="username" class="form-control" placeholder="Enter username"
                            id="username" onchange="checkUsername()" required>
                    </div>

                    <div class="form-group mb-2">
                        <input type="password" name="password" class="form-control" placeholder="Enter password"
                            id="password" required>
                    </div>

                    <div class="form-group mb-2">
                        <input type="password" class="form-control" placeholder="Enter passwordCheck" id="passwordCheck"
                            onchange="checkPassword()" required>
                    </div>

                    <div class="form-group mb-2">
                        <input type="email" name="email" class="form-control" placeholder="Enter email" id="email"
                            required>
                    </div>

                    <button type="submit" class="btn btn-primary">회원가입</button>
                </form>

            </div>
        </div>

        <script>
            let checkAbleUsername = false;
            let checkSamePassword = false;

            function valid() {
                if (checkAbleUsername == true && checkSamePassword == true) {
                    return true;
                } else {
                    alert("아이디 중복체크 해주세요.");
                    return false;
                }
            }

            function checkPassword() {
                checkSamePassword = false;
                if ($("#password").val() == $("#passwordCheck").val()) {
                    checkSamePassword = true;
                } else {
                    alert("패스워드가 동일하지 않습니다");
                    $("#passwordCheck").val("");
                }
            }

            function checkUsername() {
                checkAbleUsername = false;
                let username = $("#username").val();
                $.ajax({
                    type: "get",
                    url: "/user/usernameCheck?username=" + username
                })
                    .done((res) => {
                        console.log(res);
                        alert(res.msg);
                        if (res.data == true) {
                            checkAbleUsername = true;
                        }
                    })
                    .fail((err) => {
                        console.log(err);
                    })
            }
        </script>
        <%@ include file="../layout/footer.jsp" %>