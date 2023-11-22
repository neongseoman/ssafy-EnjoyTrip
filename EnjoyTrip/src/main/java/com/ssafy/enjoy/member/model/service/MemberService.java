package com.ssafy.enjoy.member.model.service;

import com.ssafy.enjoy.member.model.vo.MemberVo;
import com.ssafy.enjoy.member.model.dto.ModifyMemberDto;
import com.ssafy.enjoy.member.model.dto.MemberDto;

public interface MemberService {
	MemberVo loginMember(MemberDto member, String ip) throws Exception;

	int idCheck(String id) throws Exception;

	void joinMember(MemberDto member) throws Exception;

	void updateMember(ModifyMemberDto member) throws Exception;
	int isLogin(String id) throws  Exception;
	void updateLoginCondition(String id) throws Exception;
	void logout(String id) throws Exception;
	void deleteMember(MemberDto member) throws Exception;
}
