package shop.mtcoding.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.ResponseDto;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.service.BoardService;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final Gson gson = new Gson();

    @GetMapping({ "/", "board/list" })
    public String list() {
        return "board/list";
    }

    @GetMapping("/board/getList")
    public @ResponseBody ResponseDto<?> getList() {
        List<Board> boardList = boardService.getBoardList();
        String data = gson.toJson(boardList);
        return new ResponseDto<>(1, "게시물 목록 불러오기 성공", data);
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
