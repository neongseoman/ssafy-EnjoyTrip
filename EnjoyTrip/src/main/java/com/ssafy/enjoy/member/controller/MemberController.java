package com.ssafy.enjoy.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoy.member.model.Member;
import com.ssafy.enjoy.member.model.ModifyMember;
import com.ssafy.enjoy.member.model.service.MemberService;

@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/session")
    public Map<String, String> sessionCheck(@RequestBody Member member, HttpSession session, HttpServletRequest request) {
        Map<String, String> result = new HashMap<String, String>();
        if (member == null || member.getUserId() == null || member.getUserPassword() == null) {
            result.put("msg", "NO");
            result.put("detail", "no input id");
        } else {
            Member userinfo = (Member) session.getAttribute("userinfo");
            if (userinfo == null) {
                System.out.println(userinfo);
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
    public Map<String, String> login(@RequestBody Member member, HttpServletRequest request) {
        Map<String, String> result = new HashMap<String, String>();
        String ip = request.getRemoteAddr();
        if (member.getUserId() == null || member.getUserPassword() == null) {
            result.put("msg", "NO");
            result.put("detail", "no id or no pw");
        } else {
            try {
                Member userinfo = memberService.loginMember(member, ip);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @PostMapping("/idCheck")
    public Map<String, String> idCheck(@RequestBody Map<String,String> id) {
        System.out.println("call idcheck");
        System.out.println(id.get("id"));
        Map<String, String> result = new HashMap<String, String>();
        if (id.get("id") == null) {
            result.put("msg", "NO");
            result.put("detail", "no id");
            return result;
        }
        try {
            if (memberService.idCheck(id.get("id")) == 0) {
                result.put("msg", "OK");
                result.put("detail", "사용가능한 id");
            } else {
                result.put("msg", "NO");
                result.put("detail", "id 중복");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "No");
            result.put("detail", e.getMessage());
        }
//		result.put("msg","OK");
//		result.put("detail","on Test config all req is OK");
        return result;
    }

    @PostMapping("/join")
    public Map<String, String> join(@RequestBody Member member) {
        Map<String, String> result = new HashMap<String, String>();
        if (member.getUserId() == null || member.getUserPassword() == null || member.getEmailId() == null || member.getEmailDomain() == null || member.getUserName() == null) {
            result.put("msg", "NO");
            result.put("detail", "모든 정보를 입력해 주세요");
        } else {
            try {
                memberService.joinMember(member);
                result.put("msg", "OK");
                result.put("detail", "회원가입 성공 로그인 해주세요");
//				result.put("detail","on Test config all req is OK");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("msg", "NO");
                result.put("detail", e.getMessage());
            }
        }
        return result;
    }

    @GetMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        Map<String, String> result = new HashMap<String, String>();
//        session.invalidate();
        result.put("msg", "OK");
        result.put("detail", "로그아웃 되었습니다.");
        return result;
    }

    @PostMapping("update")
    public Map<String, String> update(@RequestBody ModifyMember member, HttpServletRequest request, HttpSession session) {
        Map<String, String> result = new HashMap<String, String>();
        System.out.println(member.toString());
        if (member.getUserId() == null || member.getUserPassword() == null || member.getUserName() == null || member.getEmailId() == null || member.getEmailDomain() == null || member.getNewPassword() == null) {
            result.put("msg", "NO");
            result.put("detail", "모든 정보를 입력해 주세요");
        } else {
            if (member.getNewPassword().equals("")) {
                member.setNewPassword(member.getUserPassword());
            }
            try {
                Member userinfo = memberService.loginMember(member, request.getRemoteAddr());
                member.setUserPassword(userinfo.getUserPassword());
                try {
                    memberService.updateMember(member);
                    result.put("msg", "OK");
                    result.put("detail", "정보 수정이 완료 되었습니다");
                } catch (Exception e) {
                    result.put("msg", "NO");
                    result.put("detail", e.getMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                result.put("msg", "NO");
                result.put("detail", "비밀번호가 틀립니다.");
            }
        }
        return result;
    }
}
