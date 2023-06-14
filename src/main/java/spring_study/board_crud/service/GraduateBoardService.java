package spring_study.board_crud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring_study.board_crud.domain.GraduateBoard;
import spring_study.board_crud.repository.GraduateBoardRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GraduateBoardService {
    
    private final GraduateBoardRepository GraduateboardRepository;

    public List<GraduateBoard> findBoards() {
        return GraduateboardRepository.findAll();
    }

    public GraduateBoard findOne(Long boardId) {
        return GraduateboardRepository.findById(boardId).orElseThrow(NullPointerException::new);
    }

    @Transactional
    public void create(GraduateBoard Graduateboard) {
        GraduateboardRepository.save(Graduateboard);
    }

    @Transactional
    public void update(Long id, String title, String content) {
        GraduateBoard board = GraduateboardRepository.findById(id).orElseThrow(NullPointerException::new);
        board.setTitle(title);
        board.setContent(content);
    }

    @Transactional
    public void delete(GraduateBoard board) {
        GraduateboardRepository.delete(board);
    }
}
