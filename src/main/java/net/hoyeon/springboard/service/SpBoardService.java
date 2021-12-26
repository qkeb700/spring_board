package net.hoyeon.springboard.service;

import net.hoyeon.springboard.entity.SpBoard;
import net.hoyeon.springboard.repository.SpBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpBoardService {

    @Autowired
    private SpBoardRepository boardRepository;

    public void write(SpBoard spBoard){
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