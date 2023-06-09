package spring_study.board_crud.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import spring_study.board_crud.domain.Board;

//DTO : Data Transfer Object 계층 간 데이터 전송에 사용되는 객체
@Data
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}