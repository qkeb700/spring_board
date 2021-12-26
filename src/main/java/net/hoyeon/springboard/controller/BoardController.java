package net.hoyeon.springboard.controller;

import net.hoyeon.springboard.entity.SpBoard;
import net.hoyeon.springboard.service.SpBoardService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/write")
    public String spBoardWrite(){return "write";}

    @PostMapping("/write.DO")
    public String spBoardWriteDo(SpBoard spBoard){
        spBoardService.write(spBoard);
        return "";
    }

    @GetMapping("/list")
    public String spBoardList(Model model){
        model.addAttribute("list", spBoardService.boardList());
        return "list";
    }

    @GetMapping("/view") // localhost:8080/view?id=1
    public String boardView(Model model, Integer id){
        model.addAttribute("spboard", spBoardService.boardView(id));
        return "view";
    }

    @GetMapping("/modify/{id}")
    public String spBoardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("spboard", spBoardService.boardView(id));
        return "modify";
    }

    @PostMapping("/update/{id}")
    public String spBoardUpdate(@PathVariable("id") Integer id, SpBoard spBoard, MultipartFile file) throws  Exception{
        SpBoard spboardTemp = spBoardService.boardView(id);
        spboardTemp.setTitle(spBoard.getTitle());
        spboardTemp.setContent(spBoard.getContent());

        spBoardService.write(spboardTemp);
        return "redirect:/list";
    }
}
