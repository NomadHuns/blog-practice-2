package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.ResponseDto;
import shop.mtcoding.blog.dto.UserUpdateDto;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }
        model.addAttribute("principal", principal);
        return "user/updateForm";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        if (username == null || username.equals("")) {
            return "redirect:/loginForm";
        }
        if (password == null || password.equals("")) {
            return "redirect:/loginForm";
        }
        User principal = userService.login(username, password);
        if (principal == null) {
            return "redirect:/loginForm";
        }
        session.setAttribute("principal", principal);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(String username, String password, String email, String passwordCheck) {
        if (username == null || username.equals("")) {
            return "redirect:/joinForm";
        }
        if (password == null || password.equals("")) {
            return "redirect:/joinForm";
        }
        if (email == null || email.equals("")) {
            return "redirect:/joinForm";
        }
        int result = userService.join(username, password, email);
        if (result != 1) {
            return "redirect:/joinForm";
        }
        User principal = userService.login(username, password);
        session.setAttribute("principal", principal);
        return "redirect:/";
    }

    @GetMapping("/user/usernameCheck")
    public @ResponseBody ResponseDto<?> usernameCheck(String username) {
        int result = userService.checkUsername(username);
        if (result != 1) {
            return new ResponseDto<>(1, "중복된 유저네임이 존재합니다.", false);
        } else {
            return new ResponseDto<>(1, "사용가능한 유저네임입니다.", true);
        }
    }

    @PutMapping("/user/update")
    public @ResponseBody ResponseDto<?> update(@RequestBody @Validated UserUpdateDto userUpdateDto) {
        User principal = (User) session.getAttribute("principal");
        Gson gson = new Gson();
        if (principal == null) {
            return new ResponseDto<>(-1, "로그인 상태를 확인하세요", null);
        }
        int result = userService.update(userUpdateDto, principal);
        if (result != 1) {
            return new ResponseDto<>(-1, "DB 접근 중 문제가 발생하였습니다", null);
        }
        String data = gson.toJson(userUpdateDto);
        return new ResponseDto<>(1, "회원 정보 수정에 성공하였습니다.", data);
    }
}
