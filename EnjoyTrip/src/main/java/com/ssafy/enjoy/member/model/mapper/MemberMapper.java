package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import com.ssafy.enjoy.member.model.vo.MemberVo;
import com.ssafy.enjoy.member.model.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.dto.ModifyMemberDto;

@Mapper
public interface MemberMapper {

	int idCheck(String id) throws Exception ;

	MemberVo readMember(String id, String pw) throws SQLException;

	void createMember(MemberDto member) throws SQLException;

	void updateMember(ModifyMemberDto member) throws SQLException;

	String login(MemberDto m);

	void saveToken(MemberDto m);

	int isLogin(String userId);
	void addLoginCheck(String userId);
	void updateLoginCondition(String userId);

	void deleteMember(String userId) throws SQLException;

	void deleteLoginCondition(String userId) throws SQLException;

}
