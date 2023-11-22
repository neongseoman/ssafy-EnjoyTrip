package com.ssafy.enjoy.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.member.model.dto.FailResDto;
import com.ssafy.enjoy.member.model.dto.MemberDto;
import com.ssafy.enjoy.member.model.dto.MemberResDto;
import com.ssafy.enjoy.member.model.dto.ModifyMemberDto;
import com.ssafy.enjoy.member.model.dto.ResDto;
import com.ssafy.enjoy.member.model.service.MemberService;
import com.ssafy.enjoy.member.model.vo.MemberVo;
import com.ssafy.enjoy.session.SessionService;
import com.ssafy.enjoy.session.model.SessionReqDto;
import com.ssafy.util.DtoException;
import com.ssafy.util.OpenCrypt;

@RestController
@Description("Member Controller")
@RequestMapping("/reHfPyFw")
public class MemberController {
    @Autowired
    SessionService sessionService;
    @Autowired
    MemberService memberService;

    @Description("이건 로그인")
    @PostMapping("/MAqGI3Cv")
    public ResponseEntity<ResDto> login(@RequestBody MemberDto member, HttpServletRequest request) throws UnsupportedEncodingException {
        String ip = request.getRemoteAddr();
        System.out.println(member);
        String userId =(String) request.getAttribute("userId");

        String hashedUserAgent = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA1(request.getHeader("User-Agent"), "ssafy"));

        // =======>> 결론: 해쉬하고 16으로 짜른 값이 길이가 적절하다. userAgent 통째로 한 것 보다.
        if (member.getUserId() == null || member.getUserPassword() == null) {
            FailResDto failResDto = new FailResDto("No","no id or no pw");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(failResDto);
        } else {
            try {
                MemberVo userInfo = memberService.loginMember(member, ip);
                if (memberService.isLogin(userInfo.getUserId()) == 1){
                    FailResDto failResDto = new FailResDto("No","이미 로그인된 사용자");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(failResDto);
                }
                SessionReqDto sessionReqModel = new SessionReqDto(userInfo.getUserId());
                String sessionId = sessionService.sessionReq(sessionReqModel,userInfo,hashedUserAgent); // session put도 해줌.
                memberService.updateLoginCondition(userInfo.getUserId());
                System.out.println(hashedUserAgent.substring(0,10));
                sessionService.getSession(sessionId)
                        .setHashedUserAgent(hashedUserAgent.substring(0,10));

                MemberResDto memberResDto =
                        new MemberResDto("OK","로그인 성공",sessionId,userInfo.getUserName(),userInfo.getUserId());

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberResDto);
            } catch (Exception e) {
                e.printStackTrace();
                FailResDto failResDto = new FailResDto("No",e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
            }
        }
    }

    @Description("ID check")
    @PostMapping("/EpMrOzqa")
    public ResponseEntity<ResDto> idCheck(@RequestBody Map<String, String> id) {
//        System.out.println("call id check");
        System.out.println(id);
        if (id.get("id") == null) {
            FailResDto failResDto = new FailResDto("No","No CONTENT");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(failResDto);
        }
        try {
            if (memberService.idCheck(id.get("id")) == 0) {
                FailResDto failResDto = new FailResDto("Yes","사용가능한 id");
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/json").body(failResDto);
            } else {
                FailResDto failResDto = new FailResDto("No","id 중복");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(failResDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FailResDto failResDto = new FailResDto("No","ERROR");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
        }
    }

    @Description("member join")
    @PostMapping("/ZbsgU6oY")
    public ResponseEntity<ResDto> join(@RequestBody MemberDto member) {
        if (member.getUserId() == null || member.getUserPassword() == null || member.getEmailId() == null || member.getEmailDomain() == null || member.getUserName() == null) {
            FailResDto failResDto = new FailResDto("No","모든 정보를 입력해 주세요");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(failResDto);
        } else {
            try {
                memberService.joinMember(member);
                FailResDto failResDto = new FailResDto("Yes","회원가입 성공 로그인 해주세요");
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/json").body(failResDto);
            } catch (Exception e) {
                e.printStackTrace();
                FailResDto failResDto = new FailResDto("No","ERRROR");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
            }
        }
//        return result;
    }

    @Description("logout")
    @PostMapping("/tLL8srPp")
    public ResponseEntity<ResDto> logout( HttpServletRequest request) {
        String id =(String) request.getAttribute("userId");
        String sessionId = (String) request.getAttribute("sessionId");

        try{
            memberService.logout(id);
            sessionService.invalidate(sessionId);
            FailResDto failResDto = new FailResDto("Yes","로그아웃 성공");
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/json").body(failResDto);

        } catch ( Exception e) {
            e.printStackTrace();
            FailResDto failResDto = new FailResDto("No","ERRROR");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
        }
    }

    @Description("update")
    @PostMapping("yztR0IHo")
    public ResponseEntity<ResDto> update(@RequestBody ModifyMemberDto member, HttpServletRequest request, HttpSession session) throws DtoException {
        System.out.println(member.toString());
        member.setUserId((String)request.getAttribute("userId"));
        member.setUserName((String)request.getAttribute("userName"));
        if (member.getUserId() == null || member.getUserPassword() == null || member.getUserName() == null || member.getEmailId() == null || member.getEmailDomain() == null || member.getNewPassword() == null) {

            FailResDto failResDto = new FailResDto("No","내용이 없음");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(failResDto);
        } else {
            if (member.getNewPassword().equals("")) {
                member.setNewPassword(member.getUserPassword());
            }
            try {
                MemberVo userinfo = memberService.loginMember(member, request.getRemoteAddr());
                member.setUserPassword(userinfo.getUserPassword());
                try {
                    memberService.updateMember(member);

                    FailResDto failResDto = new FailResDto("Yes","로그인 성공");
                    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/json").body(failResDto);
                } catch (Exception e) {
                    e.printStackTrace();
                    FailResDto failResDto = new FailResDto("No","ERRROR");
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
                }
            } catch (Exception e) {
                e.getMessage();
                FailResDto failResDto = new FailResDto("No","ERRROR");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
            }
        }
    }

    @Description("멤버 탈퇴")
    @PostMapping("0fokQBK6")
    public ResponseEntity<ResDto> delete(HttpServletRequest request){
    	 try {
    		 MemberDto member = new MemberDto();
    		 member.setUserId((String)request.getAttribute("userId"));
    		 member.setUserName((String)request.getAttribute("userName"));
    		 memberService.deleteMember(member);
             FailResDto failResDto = new FailResDto("Yes","로그인 성공");
             return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/json").body(failResDto);
    	 }catch(Exception e) {
    		 e.printStackTrace();
             FailResDto failResDto = new FailResDto("No","회원가입 실패");
             return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
    	 }
    }
}
