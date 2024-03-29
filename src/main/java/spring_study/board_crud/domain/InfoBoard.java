package spring_study.board_crud.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "info_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id, title"})
public class InfoBoard {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    public InfoBoard(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}