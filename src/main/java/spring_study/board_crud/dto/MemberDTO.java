package spring_study.board_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring_study.board_crud.entity.MemberEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String userid;
    private String pw;
    private String name;
    private int stnumber;
    private String refreshToken;

    public static MemberDTO MemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setUserid(memberEntity.getUserid());
        memberDTO.setPw(memberEntity.getPw());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setStnumber(memberEntity.getStnumber());
        memberDTO.setRefreshToken(memberEntity.getRefreshToken());
        return memberDTO;
    }
}
