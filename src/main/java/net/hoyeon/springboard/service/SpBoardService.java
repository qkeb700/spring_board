package net.hoyeon.springboard.service;

import net.hoyeon.springboard.entity.SpBoard;
import net.hoyeon.springboard.repository.SpBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class SpBoardService {

    @Autowired
    private SpBoardRepository boardRepository;

    public void write(SpBoard spBoard, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        spBoard.setFilename(fileName);
        spBoard.setFilepath("/files/" + fileName);

        boardRepository.save(spBoard);
    }
    public List<SpBoard> boardList(){
        return boardRepository.findAll();
    }

    // 특정 게시글 불러오기
    public SpBoard boardView(Integer id){
        return boardRepository.findById(id).get();
    }
}
