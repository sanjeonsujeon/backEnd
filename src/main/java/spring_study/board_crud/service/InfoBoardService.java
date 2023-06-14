package spring_study.board_crud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring_study.board_crud.domain.InfoBoard;
import spring_study.board_crud.repository.InfoBoardRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InfoBoardService {
    
    private final InfoBoardRepository InfoboardRepository;

    public List<InfoBoard> findBoards() {
        return InfoboardRepository.findAll();
    }

    public InfoBoard findOne(Long boardId) {
        return InfoboardRepository.findById(boardId).orElseThrow(NullPointerException::new);
    }

    @Transactional
    public void create(InfoBoard Infoboard) {
        InfoboardRepository.save(Infoboard);
    }

    @Transactional
    public void update(Long id, String title, String content) {
        InfoBoard board = InfoboardRepository.findById(id).orElseThrow(NullPointerException::new);
        board.setTitle(title);
        board.setContent(content);
    }

    @Transactional
    public void delete(InfoBoard board) {
        InfoboardRepository.delete(board);
    }
}
