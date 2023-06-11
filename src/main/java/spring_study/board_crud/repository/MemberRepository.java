package spring_study.board_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_study.board_crud.entity.MemberEntity;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
   Optional<MemberEntity> findByUserid(String userid);
   Optional<MemberEntity> findByStnumber(int stnumber);

}