package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import com.ssafy.enjoy.member.model.MemberVO;
import com.ssafy.enjoy.member.model.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.Member;
import com.ssafy.enjoy.member.model.ModifyMember;

@Mapper
public interface MemberMapper {

	int idCheck(String id) throws Exception ;

	MemberVO readMember(String id, String pw) throws SQLException;

	void createMember(MemberDto member) throws SQLException;

	void updateMember(ModifyMember member) throws SQLException;

	String login(Member m);

	void saveToken(Member m);

	int isLogin(String userId);
	void addLoginCheck(String userId);
	void updateLoginCondition(String userId);

	void deleteMember(String userId) throws SQLException;

	void deleteLoginCondition(String userId) throws SQLException;

}
