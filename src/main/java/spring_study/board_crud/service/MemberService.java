package spring_study.board_crud.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import spring_study.board_crud.dto.MemberDTO;
import spring_study.board_crud.entity.MemberEntity;
import spring_study.board_crud.repository.MemberRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        
    }

    public boolean IDcheck(String userid) {
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(userid);
        //System.out.println(byUserid);
        return byUserid.isPresent();

    }

    public boolean stnumCheck(int stnumber) {
        Optional<MemberEntity> byStnumber = memberRepository.findByStnumber(stnumber);
        //System.out.println(byStnumber);
        return byStnumber.isPresent();
    }

    public boolean PWchecking(MemberDTO memberDTO) {
        String userid = memberDTO.getUserid();
        String pw = memberDTO.getPw();
        System.out.println(memberDTO.getUserid());
        System.out.println(memberDTO.getPw());
        System.out.println(userid +" "+pw);
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(userid);
        if(IDcheck(userid)==true){
            MemberEntity user = byUserid.get();
            if (user.getPw().equals(pw)) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
        
    }
}
