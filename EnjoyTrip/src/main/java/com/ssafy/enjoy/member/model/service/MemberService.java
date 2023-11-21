package com.ssafy.enjoy.member.model.service;

import com.ssafy.enjoy.member.model.MemberVO;
import com.ssafy.enjoy.member.model.ModifyMember;
import com.ssafy.enjoy.member.model.dto.MemberDto;

public interface MemberService {
	MemberVO loginMember(MemberDto member, String ip) throws Exception;

	int idCheck(String id) throws Exception;

	void joinMember(MemberDto member) throws Exception;

	void updateMember(ModifyMember member) throws Exception;
	int isLogin(String id) throws  Exception;
	void updateLoginCondition(String id) throws Exception;
	void logout(String id) throws Exception;
	void deleteMember(MemberDto member) throws Exception;
}
