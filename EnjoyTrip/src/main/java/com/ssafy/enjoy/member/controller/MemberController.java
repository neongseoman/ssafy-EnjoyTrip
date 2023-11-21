package com.ssafy.enjoy.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssafy.enjoy.member.model.dto.FailResDto;
import com.ssafy.enjoy.member.model.dto.MemberResDto;
import com.ssafy.enjoy.member.model.dto.ResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.member.model.Member;
import com.ssafy.enjoy.member.model.ModifyMember;
import com.ssafy.enjoy.member.model.service.MemberService;
import com.ssafy.enjoy.session.SessionService;
import com.ssafy.enjoy.session.model.SessionReqModel;

@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    SessionService sessionService;

    @Autowired
    MemberService memberService;

    @PostMapping("/session")
    public Map<String,String> sessionCheck(@RequestBody Member member, HttpSession session, HttpServletRequest request) {
    	
        Map<String, String> result = new HashMap<java.lang.String, java.lang.String>();
        if (member == null || member.getUserId() == null || member.getUserPassword() == null) {
            result.put("msg", "NO");
            result.put("detail", "no input id");
        } else {
            Member userinfo = (Member) session.getAttribute("userinfo");
            if (userinfo == null) {
                result.put("msg", "NO");
                result.put("detail", "already logout or session expired");
            } else if (userinfo.getUserId().equals(member.getUserId()) && userinfo.getUserPassword().equals(member.getUserPassword())) {
                result.put("msg", "OK");
                result.put("detail", "login success");
                result.put("name", userinfo.getUserName());
                result.put("email_id", userinfo.getEmailId());
                result.put("email_domain", userinfo.getEmailDomain());
            } else {
                result.put("msg", "NO");
                result.put("detail", "already logout or session expired");
            }
        }
        return result;
    }

    @PostMapping("/login")
    public ResponseEntity<ResDto> login(@RequestBody Member member, HttpServletRequest request) {
        Map<String, String> result = new HashMap<String, String>();
        String ip = request.getRemoteAddr();
        System.out.println(member);
        if (member.getUserId() == null || member.getUserPassword() == null) {
            FailResDto failResDto = new FailResDto("No","no id or no pw");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(failResDto);
        } else {
            try {
                Member userinfo = memberService.loginMember(member, ip);
                if (memberService.isLogin(userinfo.getUserId()) == 1){
                    FailResDto failResDto = new FailResDto("No","이미 로그인된 사용자");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(failResDto);

                }
                SessionReqModel sessionReqModel = new SessionReqModel(userinfo.getUserId());
                String sessionId = sessionService.sessionReq(sessionReqModel);
                memberService.updateLoginCondition(userinfo.getUserId());
                System.out.println(sessionService.getSession(sessionId).toString());
                MemberResDto memberResDto =
                        new MemberResDto("OK",sessionId,"login success",userinfo.getUserName(),userinfo.getUserId());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberResDto);
            } catch (Exception e) {
                e.printStackTrace();
                FailResDto failResDto = new FailResDto("No","Unchecked Error");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
            }
        }
    }

    @PostMapping("/idCheck")
    public ResponseEntity<ResDto> idCheck(@RequestBody Map<String, String> id) {
//        System.out.println("call id check");
//        System.out.println(id.get("id"));
        Map<String, String> result = new HashMap<java.lang.String, java.lang.String>();
        if (id.get("id") == null) {
            FailResDto failResDto = new FailResDto("No","no id");
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

    @PostMapping("/join")
    public ResponseEntity<ResDto> join(@RequestBody Member member) {
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

    @PostMapping("/logout")
    public ResponseEntity<ResDto> logout(@RequestBody Map<String, String> id) {
        try{
            System.out.println("logout :  " + id.toString());
//        session.invalidate();
            FailResDto failResDto = new FailResDto("Yes","로그인 성공");
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/json").body(failResDto);

        } catch ( Exception e) {
            e.printStackTrace();
            FailResDto failResDto = new FailResDto("No","ERRROR");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(failResDto);
        }
    }

    @PostMapping("update")
    public ResponseEntity<ResDto> update(@RequestBody ModifyMember member, HttpServletRequest request, HttpSession session) {
        System.out.println(member.toString());
        if (member.getUserId() == null || member.getUserPassword() == null || member.getUserName() == null || member.getEmailId() == null || member.getEmailDomain() == null || member.getNewPassword() == null) {

            FailResDto failResDto = new FailResDto("No","내용이 없음");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(failResDto);
        } else {
            if (member.getNewPassword().equals("")) {
                member.setNewPassword(member.getUserPassword());
            }
            try {
                Member userinfo = memberService.loginMember(member, request.getRemoteAddr());
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
    @PostMapping("delete")
    public ResponseEntity<ResDto> delete(@RequestBody Member member){
    	 try {
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
