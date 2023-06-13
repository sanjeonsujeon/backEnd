package spring_study.board_crud.api;

import java.nio.channels.MembershipKey;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.net.HttpHeaders;

import lombok.RequiredArgsConstructor;
import spring_study.board_crud.dto.MemberDTO;
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
            String authorization = tokenProvider.createAccessToken(700000,memberDTO.getUserid());
            String refreshtoken = tokenProvider.createToken(1800000);
            System.out.println(authorization);
            System.out.println(refreshtoken);
            memberService.saveRefreshToken(memberDTO.getUserid(), refreshtoken);
            return ResponseEntity.status(200)
                    .header("Authorization", tokenProvider.createAccessToken(700000,memberDTO.getUserid()))
                    .header("Refreshtoken", tokenProvider.createToken(1800000))
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID_PW 불일치");
        }
    }
    /* 
    @PostMapping("api/oauth/token")
    public ResponseEntity refresh(@RequestHeader("authorization") String authorization,
            @RequestHeader("refreshtoken") String refreshtoken, Authentication authentication) {
        TokenProvider tokenProvider = new TokenProvider();
        String str = "ppap";
        System.out.println(authorization);
        System.out.println(refreshtoken);
        System.out.println(authentication.getUsername());
        if (tokenProvider.validateToken(authorization)) {
            String Authorization = tokenProvider.createAccessToken(700000,str);
            String RefreshToken = tokenProvider.createToken(1800000);
            return ResponseEntity.status(200)
                    .header("Authorization", Authorization)
                    .header("Refreshtoken", RefreshToken)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("토큰 갱신 불가");
        }
    }*/
   /* @GetMapping("/my")
    public MemberDTO findUser(Authentication authentication) throws Exception {
        if (authentication == null) {
            throw new Exception("회원 정보를 찾을 수 없습니다.");
        }
        return memberService.findUserid(authentication.getUsername());
    }*/

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
        // System.out.println(userid);
        // System.out.println(memberService.IDcheck(userid));
        if (memberService.IDcheck(userid) == true) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID중복");
        } else {
            return ResponseEntity.ok().build();
        }

    }
}
