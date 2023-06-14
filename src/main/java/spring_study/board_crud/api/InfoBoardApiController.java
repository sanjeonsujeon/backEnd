package spring_study.board_crud.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_study.board_crud.domain.InfoBoard;
import spring_study.board_crud.dto.InfoBoardDeleteDto;
import spring_study.board_crud.dto.InfoBoardDto;
import spring_study.board_crud.service.InfoBoardService;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000/")
public class InfoBoardApiController {
    
        private final InfoBoardService InfoboardService; // Autowired로 스프링 빈에 등록

    //게시판 띄우기
    @GetMapping("/api/infoboard-list")
    public WrapperClass board_list() {
        List<InfoBoard> InfoboardList = InfoboardService.findBoards();
        List<InfoBoardDto> InfoboardDtoList = InfoboardList.stream().map(b -> new InfoBoardDto(b)).collect(Collectors.toList());
        return new WrapperClass(InfoboardDtoList);
    }

    @GetMapping("/api/infoboard-detail/{boardId}") //게시글 상세보기
    public WrapperClass board_detail(@PathVariable("boardId") Long InfoboardId) {
        InfoBoard Infoboard = InfoboardService.findOne(InfoboardId);
        InfoBoardDto InfoboardDto = new InfoBoardDto(Infoboard);
        return new WrapperClass(InfoboardDto);
    }

    @PostMapping("/api/infocreate-board") //게시글 작성
    public ResponseEntity create_board(@RequestBody InfoBoardDto InfoboardDto) {
        System.out.println("create_board/boardDto = " + InfoboardDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED; // 201 잘 생성되었음을 의미
        try {
            InfoBoard Infoboard = new InfoBoard(
                    InfoboardDto.getId(),
                    InfoboardDto.getTitle(),
                    InfoboardDto.getContent()
            );
            InfoboardService.create(Infoboard);
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST; // 400 에러
            System.out.println("create_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }

    @PutMapping("/api/infoupdate-board") //게시글 수정
    public ResponseEntity update_board(@RequestBody InfoBoardDto InfoboardDto) {
        System.out.println("update_board/boardDto = " + InfoboardDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT; // 204 -> 수정이 정상적으로 완료됬음을 의미
        try {
            InfoboardService.update(InfoboardDto.getId(), InfoboardDto.getTitle(), InfoboardDto.getContent());
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST; // 400 에러
            System.out.println("update_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }

    @DeleteMapping("/api/infodelete-board") //게시글 삭제
    public ResponseEntity delete_board(@RequestBody InfoBoardDeleteDto InfoboardDeleteDto) {
        System.out.println("delete_board/boardDeleteDto = " + InfoboardDeleteDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT; // 204
        try {
            InfoBoard board = InfoboardService.findOne(InfoboardDeleteDto.getId());
            InfoboardService.delete(board);
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST;
            System.out.println("delete_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }
}
