package com.ssafy.enjoy.member.model.service;

import com.ssafy.enjoy.member.model.Member;
import com.ssafy.enjoy.member.model.ModifyMember;

public interface MemberService {

	Member loginMember(Member member, String ip) throws Exception;
	Member sessionLogin(Member member) throws Exception;

	int idCheck(String id) throws Exception;

	void joinMember(Member member) throws Exception;

	void updateMember(ModifyMember member) throws Exception;

}
