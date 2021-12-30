package net.hoyeon.springboard.controller;

import net.bytebuddy.TypeCache;
import net.hoyeon.springboard.entity.SpBoard;
import net.hoyeon.springboard.service.SpBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private SpBoardService spBoardService;

    @GetMapping("/board/write")
    public String boardWriteForm(){return "boardwrite";}

    @PostMapping("/board/writepro")
    public String boardWritePro(SpBoard spBoard, Model model, MultipartFile file) throws Exception{
        spBoardService.write(spBoard, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort="id", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword){

        Page<SpBoard> list = null;

        if(searchKeyword == null){
            list = spBoardService.boardList(pageable);
        } else{
            list = spBoardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8080/view?id=1
    public String boardView(Model model, Integer id){
        model.addAttribute("board", spBoardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/modify/{id}")
    public String spBoardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board", spBoardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String spBoardUpdate(@PathVariable("id") Integer id, SpBoard spBoard, MultipartFile file) throws  Exception{
        SpBoard spboardTemp = spBoardService.boardView(id);
        spboardTemp.setTitle(spBoard.getTitle());
        spboardTemp.setContent(spBoard.getContent());

        spBoardService.write(spboardTemp, file);
        return "redirect:/board/list";
    }
}
