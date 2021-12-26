package net.hoyeon.springboard.entity;

import lombok.Data;
import net.hoyeon.springboard.CurrentDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class SpBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String writer;
    private String pwd;
    private String title;
    private String content;
    private String wdate = CurrentDate.curDate();
}