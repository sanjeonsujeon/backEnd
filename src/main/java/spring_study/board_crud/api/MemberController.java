package spring_study.board_crud.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import spring_study.board_crud.dto.MemberDTO;
import spring_study.board_crud.service.MemberService;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://172.22.200.51:3000/")
public class MemberController {
    //생성자 주입
    private final MemberService memberService;
    
    @PostMapping("api/signup-pp")
    public ResponseEntity save(@RequestBody MemberDTO memberDTO){
        memberService.save(memberDTO);
        return ResponseEntity.ok().build();
    }
}
