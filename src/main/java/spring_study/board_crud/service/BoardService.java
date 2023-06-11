package spring_study.board_crud.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_study.board_crud.domain.Board;
import spring_study.board_crud.dto.BoardDto;
import spring_study.board_crud.repository.BoardRepository;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(NullPointerException::new);
    }

    @Transactional
    public void create(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void update(Long id, String title, String content){
        Board board = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        board.setTitle(title);
        board.setContent(content);
    }
    @Transactional
    public void delete(Board board) {
        boardRepository.delete(board);
    }
    /*
    public Page<BoardDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 10; // 한 페이지에 보여줄 글 갯수
        //한페이지당 10개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작함
        Page<BoardEntity> boardEntities = 
            boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
    }
    */
}
