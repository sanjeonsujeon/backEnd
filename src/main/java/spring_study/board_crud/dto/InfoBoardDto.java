package spring_study.board_crud.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import spring_study.board_crud.domain.InfoBoard;

//DTO : Data Transfer Object 계층 간 데이터 전송에 사용되는 객체
@Data
@NoArgsConstructor
public class InfoBoardDto {
    private Long id;
    private String title;
    private String content;

    public InfoBoardDto(InfoBoard Infoboard) {
        this.id = Infoboard.getId();
        this.title = Infoboard.getTitle();
        this.content = Infoboard.getContent();
    }
}
