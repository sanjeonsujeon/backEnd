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
    private String user_id;
    private String pw;
    private String name;
    private int st_num;

    public static MemberDTO MemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setUser_id(memberEntity.getUser_id());
        memberDTO.setPw(memberEntity.getPw());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setSt_num(memberEntity.getSt_num());
        return memberDTO;
    }
}
