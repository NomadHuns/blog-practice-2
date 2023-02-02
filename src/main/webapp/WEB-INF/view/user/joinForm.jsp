<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="container my-3">
            <div class="container">
                <form action="/join" method="post" onsubmit="return valid()">
                    <div class="form-group mb-2 ">
                        <input type="text" name="username" class="form-control" placeholder="Enter username"
                            id="username" onchange="checkUsername()">
                    </div>

                    <div class="form-group mb-2">
                        <input type="password" name="password" class="form-control" placeholder="Enter password"
                            id="password">
                    </div>

                    <div class="form-group mb-2">
                        <input type="password" class="form-control" placeholder="Enter passwordCheck"
                            id="passwordCheck">
                    </div>

                    <div class="form-group mb-2">
                        <input type="email" name="email" class="form-control" placeholder="Enter email" id="email">
                    </div>

                    <button type="submit" class="btn btn-primary">회원가입</button>
                </form>

            </div>
        </div>

        <script>
            let check = false;

            function valid() {
                if (check === true) {
                    return true;
                } else {
                    alert("아이디 중복체크 해주세요.");
                    return false;
                }
            }

            function checkUsername() {
                check = false;
                let username = $("#username").val();
                $.ajax({
                    type: "get",
                    url: "/user/usernameCheck?username=" + username
                })
                    .done((res) => {
                        console.log(res);
                        alert(res.msg);
                        if (res.data == true) {
                            check == true;
                        }
                    })
                    .fail((err) => {
                        console.log(err);
                    })
            }
        </script>
        <%@ include file="../layout/footer.jsp" %>