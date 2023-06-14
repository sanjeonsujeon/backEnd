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
import spring_study.board_crud.domain.GraduateBoard;
import spring_study.board_crud.dto.GraduateBoardDeleteDto;
import spring_study.board_crud.dto.GraduateBoardDto;
import spring_study.board_crud.service.GraduateBoardService;


@RestController
@RequiredArgsConstructor
public class GraduateBoardApiController {
        
        private final GraduateBoardService GraduateboardService; // Autowired로 스프링 빈에 등록

    //게시판 띄우기
    @GetMapping("/api/graduateboard-list")
    public WrapperClass board_list() {
        List<GraduateBoard> GraduateboardList = GraduateboardService.findBoards();
        List<GraduateBoardDto> GraduateboardDtoList = GraduateboardList.stream().map(b -> new GraduateBoardDto(b)).collect(Collectors.toList());
        return new WrapperClass(GraduateboardDtoList);
    }

    @GetMapping("/api/graduateboard-detail/{boardId}") //게시글 상세보기
    public WrapperClass board_detail(@PathVariable("boardId") Long GraduateboardId) {
        GraduateBoard Graduateboard = GraduateboardService.findOne(GraduateboardId);
        GraduateBoardDto GraduateboardDto = new GraduateBoardDto(Graduateboard);
        return new WrapperClass(GraduateboardDto);
    }

    @PostMapping("/api/graduatecreate-board") //게시글 작성
    public ResponseEntity create_board(@RequestBody GraduateBoardDto GraduateboardDto) {
        System.out.println("create_board/boardDto = " + GraduateboardDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED; // 201 잘 생성되었음을 의미
        try {
            GraduateBoard Graduateboard = new GraduateBoard(
                    GraduateboardDto.getId(),
                    GraduateboardDto.getTitle(),
                    GraduateboardDto.getContent()
            );
            GraduateboardService.create(Graduateboard);
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST; // 400 에러
            System.out.println("create_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }

    @PutMapping("/api/graduateupdate-board") //게시글 수정
    public ResponseEntity update_board(@RequestBody GraduateBoardDto GraduateboardDto) {
        System.out.println("update_board/boardDto = " + GraduateboardDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT; // 204 -> 수정이 정상적으로 완료됬음을 의미
        try {
            GraduateboardService.update(GraduateboardDto.getId(), GraduateboardDto.getTitle(), GraduateboardDto.getContent());
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST; // 400 에러
            System.out.println("update_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }

    @DeleteMapping("/api/graduatedelete-board") //게시글 삭제
    public ResponseEntity delete_board(@RequestBody GraduateBoardDeleteDto GraduateboardDeleteDto) {
        System.out.println("delete_board/boardDeleteDto = " + GraduateboardDeleteDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT; // 204
        try {
            GraduateBoard board = GraduateboardService.findOne(GraduateboardDeleteDto.getId());
            GraduateboardService.delete(board);
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST;
            System.out.println("delete_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }
}
