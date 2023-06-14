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
    private String userid;

    @Column
    private String pw;

    @Column
    private String name;

    @Column(unique = true)
    private int stnumber;

    @Column
    private String refreshToken;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserid(memberDTO.getUserid());
        memberEntity.setPw(memberDTO.getPw());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setStnumber(memberDTO.getStnumber());
        memberEntity.setRefreshToken(memberDTO.getRefreshToken());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setUserid(memberDTO.getUserid());
        memberEntity.setPw(memberDTO.getPw());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setStnumber(memberDTO.getStnumber());
        memberEntity.setRefreshToken(memberDTO.getRefreshToken());
        return memberEntity;
    }

}
