package spring_study.board_crud.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import spring_study.board_crud.domain.GraduateBoard;
@Data
@NoArgsConstructor
public class GraduateBoardDto {
    private Long id;
    private String title;
    private String content;

    public GraduateBoardDto(GraduateBoard Graduateboard) {
        this.id = Graduateboard.getId();
        this.title = Graduateboard.getTitle();
        this.content = Graduateboard.getContent();
    }
}
