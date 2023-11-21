package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.dto.MemberDto;
import com.ssafy.enjoy.member.model.ModifyMember;

@Mapper
public interface MemberMapper {

	int idCheck(String id) throws Exception ;

	MemberDto readMember(String id, String pw) throws SQLException;

	void createMember(MemberDto member) throws SQLException;

	void updateMember(ModifyMember member) throws SQLException;

	public String login(MemberDto m);

	public void saveToken(MemberDto m);

	int isLogin(String userId);
	void addLoginCheck(String userId);
	void updateLoginCondition(String userId);

	void deleteMember(String userId) throws SQLException;

	void deleteLoginCondition(String userId) throws SQLException;

}
