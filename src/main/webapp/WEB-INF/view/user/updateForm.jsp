<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="container my-3">
            <div class="container">
                <form>
                    <div class="form-group mb-2">
                        <input type="text" name="username" class="form-control" placeholder="Enter username"
                            id="username" value="${principal.username}" readonly>
                    </div>

                    <div class="form-group mb-2">
                        <input type="password" name="password" class="form-control" placeholder="Enter password"
                            id="password" value="${principal.password}" required>
                    </div>

                    <div class="form-group mb-2">
                        <input type="email" name="email" class="form-control" placeholder="Enter email" id="email"
                            value="${principal.email}" required>
                    </div>

                    <button type="button" class="btn btn-primary" onclick="updateUserInfo()">회원수정</button>
                </form>

            </div>
        </div>

        <script>
            function updateUserInfo() {
                let user = {
                    username: $("#username").val(),
                    password: $("#password").val(),
                    email: $("#email").val(),
                }
                console.log(JSON.stringify(user));
                $.ajax({
                    type: "put",
                    url: "/user/update",
                    headers: {
                        'Content-type': 'application/json; charset=UTF-8',
                    },
                    data: JSON.stringify(user),
                })
                    .done((res) => {
                        alert("성공");
                        console.log(res);
                    })
                    .fail(() => { })
            }
        </script>
        <%@ include file="../layout/footer.jsp" %>