package spring_study.board_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.board_crud.domain.GraduateBoard;

public interface GraduateBoardRepository extends JpaRepository<GraduateBoard, Long> {
    
}
