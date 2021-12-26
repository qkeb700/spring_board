package net.hoyeon.springboard.repository;

import net.hoyeon.springboard.entity.SpBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpBoardRepository extends JpaRepository<SpBoard, Integer> {

}
