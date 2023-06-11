package spring_study.board_crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import spring_study.board_crud.dto.MemberDTO;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String user_id;

    @Column
    private String pw;

    @Column(unique = true)
    private String name;

    @Column
    private int st_num;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUser_id(memberDTO.getUser_id());
        memberEntity.setPw(memberDTO.getPw());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setSt_num(memberDTO.getSt_num());
        return memberEntity;
    }


    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setUser_id(memberDTO.getUser_id());
        memberEntity.setPw(memberDTO.getPw());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setSt_num(memberDTO.getSt_num());
        return memberEntity;
    }
    
}
