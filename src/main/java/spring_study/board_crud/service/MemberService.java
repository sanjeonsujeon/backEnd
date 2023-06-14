package spring_study.board_crud.service;

import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import spring_study.board_crud.dto.MemberDTO;
import spring_study.board_crud.entity.MemberEntity;
import spring_study.board_crud.repository.MemberRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean deleteRefreshToken(String userid) {
        Optional<MemberEntity> memberOptional = memberRepository.findByRefreshToken(userid);
        if (memberOptional.isPresent()) {
            MemberEntity memberEntity = memberOptional.get();
            memberEntity.setRefreshToken(null); // refreshtoken을 null로 설정
            memberRepository.save(memberEntity); // 변경된 엔티티 저장
            return true;
        }
        return false;
    }

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public void saveRefreshToken(String userid, String refreshToken) {
        MemberEntity memberEntity = memberRepository.findByUserid(userid).orElseThrow(() -> new NotFoundException("no user id"));
        memberEntity.setRefreshToken(refreshToken);
        memberRepository.save(memberEntity);
    }

    public boolean IDcheck(String userid) {
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(userid);
        return byUserid.isPresent();
    }

    public MemberEntity getEntity(String refreshToken) {
        Optional<MemberEntity> memberOptional = memberRepository.findByRefreshToken(refreshToken);
        if (memberOptional.isPresent()) {
            MemberEntity memberEntity = memberOptional.get();
            return memberEntity;
        }
        throw new IllegalArgumentException("MemberEntity not found for userid: " + refreshToken);
    }

    public String getRefreshToken(String refreshToken) {
        Optional<MemberEntity> memberOptional = memberRepository.findByRefreshToken(refreshToken);
        if (memberOptional.isPresent()) {
            MemberEntity memberEntity = memberOptional.get();
            return memberEntity.getRefreshToken();
        }
        throw new IllegalArgumentException("getRefreshToken don't get for userid: " + refreshToken);
    }

    public boolean stnumCheck(int stnumber) {
        Optional<MemberEntity> byStnumber = memberRepository.findByStnumber(stnumber);
        return byStnumber.isPresent();
    }

    public boolean PWchecking(MemberDTO memberDTO) {
        String userid = memberDTO.getUserid();
        String pw = memberDTO.getPw();
        System.out.println(memberDTO.getUserid());
        System.out.println(memberDTO.getPw());
        System.out.println(userid + " " + pw);
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(userid);
        if (IDcheck(userid) == true) {
            MemberEntity user = byUserid.get();
            if (user.getPw().equals(pw)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
