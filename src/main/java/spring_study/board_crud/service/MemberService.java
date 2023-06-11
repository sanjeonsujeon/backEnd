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
        System.out.println(memberEntity.getStnumber());
        memberRepository.save(memberEntity);
    }

    public boolean IDcheck(String userid) {
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(userid);
        //System.out.println(byUserid);
        if (byUserid.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
