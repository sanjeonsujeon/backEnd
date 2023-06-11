package spring_study.board_crud.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring_study.board_crud.dto.MemberDTO;
import spring_study.board_crud.entity.MemberEntity;
import spring_study.board_crud.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
    
}
