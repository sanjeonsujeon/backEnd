package spring_study.board_crud.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "graduate_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id, title"})
public class GraduateBoard {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    public GraduateBoard(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}