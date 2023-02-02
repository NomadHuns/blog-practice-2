package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({ "/", "board/list" })
    public String list() {
        return "board/list";
    }

    @GetMapping("/board/updateForm")
    public String updateForm() {
        return "board/updateForm";
    }

    @GetMapping("/board/writeForm")
    public String saveForm() {
        return "board/writeForm";
    }

    @GetMapping("/detail")
    public String detail() {
        return "board/detail";
    }
}
