package spring_study.board_crud.api;

import java.nio.channels.MembershipKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import spring_study.board_crud.dto.MemberDTO;
import spring_study.board_crud.service.MemberService;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {
    //생성자 주입
    private final MemberService memberService;
    //로그인 API
    @PostMapping("api/signup-pp")
    public ResponseEntity save(@RequestBody MemberDTO memberDTO){
        if(memberService.stnumCheck(memberDTO.getStnumber())==true){
            return ResponseEntity.status(800).build();
        }else{
            memberService.save(memberDTO);
            return ResponseEntity.ok().build();
        }
    }

    //ID중복 확인

    @PostMapping("api/IDcheck")
    public ResponseEntity IDcheck(@RequestParam("userid") String userid){
        //System.out.println(userid);
        //System.out.println(memberService.IDcheck(userid));
        if(memberService.IDcheck(userid) == true){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID중복");
        }else{
            return ResponseEntity.ok().build();
        }

    }
}
