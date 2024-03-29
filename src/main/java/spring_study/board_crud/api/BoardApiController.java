package spring_study.board_crud.api;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import spring_study.board_crud.dto.BoardDeleteDto;
import spring_study.board_crud.service.BoardService;
import spring_study.board_crud.domain.Board;
import spring_study.board_crud.dto.BoardDto;



import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000/")
public class BoardApiController {
    private final BoardService boardService; // Autowired로 스프링 빈에 등록

    @GetMapping("/api/total-ids")
    public void getTotalIds(HttpServletResponse response) {
        String totalcount = Long.toString(boardService.getTotalIds());
        response.setHeader("totalcount", totalcount);
    }

    // 게시판 띄우기
    @GetMapping("/api/board-list") // 게시판 띄우기
    public WrapperClass board_list(@RequestParam("pageNumber") int pageNumber,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        pageable = PageRequest.of(pageNumber, pageable.getPageSize(), pageable.getSort());
        Page<Board> boardList = boardService.findBoards(pageable);
        List<BoardDto> boardDtoList = boardList.stream().map(b -> new BoardDto(b)).collect(Collectors.toList());
        return new WrapperClass(boardDtoList);
    }

    @GetMapping("/api/board-detail/{boardId}") // 게시글 상세보기
    public WrapperClass board_detail(@PathVariable("boardId") Long boardId) {
        Board board = boardService.findOne(boardId);
        BoardDto boardDto = new BoardDto(board);
        return new WrapperClass(boardDto);
    }

    @PostMapping("/api/create-board") // 게시글 작성
    public ResponseEntity create_board(@RequestBody BoardDto boardDto) {
        System.out.println("create_board/boardDto = " + boardDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED; // 201 잘 생성되었음을 의미
        try {
            Board board = new Board(
                    boardDto.getId(),
                    boardDto.getTitle(),
                    boardDto.getContent());
            boardService.create(board);
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST; // 400 에러
            System.out.println("create_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }

    @PutMapping("/api/update-board") // 게시글 수정
    public ResponseEntity update_board(@RequestBody BoardDto boardDto) {
        System.out.println("update_board/boardDto = " + boardDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT; // 204 -> 수정이 정상적으로 완료됬음을 의미
        try {
            boardService.update(boardDto.getId(), boardDto.getTitle(), boardDto.getContent());
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST; // 400 에러
            System.out.println("update_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }

    @DeleteMapping("/api/delete-board") // 게시글 삭제
    public ResponseEntity delete_board(@RequestBody BoardDeleteDto boardDeleteDto) {
        System.out.println("delete_board/boardDeleteDto = " + boardDeleteDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT; // 204
        try {
            Board board = boardService.findOne(boardDeleteDto.getId());
            boardService.delete(board);
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST;
            System.out.println("delete_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }
}