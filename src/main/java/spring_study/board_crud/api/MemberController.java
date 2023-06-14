package spring_study.board_crud.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_study.board_crud.dto.MemberDTO;
import spring_study.board_crud.entity.MemberEntity;
import spring_study.board_crud.service.MemberService;
import spring_study.board_crud.service.TokenProvider;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 로그인 API
    @PostMapping("api/login")
    public ResponseEntity login(@RequestBody MemberDTO memberDTO) {
        if (memberService.PWchecking(memberDTO)) {
            TokenProvider tokenProvider = new TokenProvider();
            String refreshtoken = tokenProvider.createToken();
            memberService.saveRefreshToken(memberDTO.getUserid(), refreshtoken);
            return ResponseEntity.status(200)
                    .header("Refreshtoken", refreshtoken)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID_PW 불일치");
        }
    }

    @PostMapping("api/check-token")
    public ResponseEntity checkToken(@RequestHeader("refreshtoken") String Token) {
        String DBtoken = memberService.getRefreshToken(Token);
        if (Token.equals(DBtoken)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰값 불일치");
        }
    }

    @GetMapping("api/logout")
    public ResponseEntity<String> logout(@RequestHeader("refreshtoken") String Token) {
        MemberEntity memberEntity = memberService.getEntity(Token);
        boolean isDeleted = memberService.deleteRefreshToken(memberEntity.getUserid());
        if (isDeleted) {
            return ResponseEntity.ok("로그아웃이 완료됨");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃에 실패했습니다");
        }
    }

    // 회원가입 API
    @PostMapping("api/signup-pp")
    public ResponseEntity save(@RequestBody MemberDTO memberDTO) {
        if (memberService.stnumCheck(memberDTO.getStnumber()) == true) {
            return ResponseEntity.status(800).build();
        } else {
            memberService.save(memberDTO);
            return ResponseEntity.ok().build();
        }
    }

    // ID중복 확인
    @PostMapping("api/IDcheck")
    public ResponseEntity IDcheck(@RequestParam("userid") String userid) {
        if (memberService.IDcheck(userid) == true) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID중복");
        } else {
            return ResponseEntity.ok().build();
        }

    }
}
