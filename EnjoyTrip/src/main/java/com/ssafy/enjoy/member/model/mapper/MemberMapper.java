package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.Member;
import com.ssafy.enjoy.member.model.ModifyMember;

@Mapper
public interface MemberMapper {

	int idCheck(String id) throws Exception ;

	Member readMember(String id, String pw) throws SQLException;

	void createMember(Member member) throws SQLException;

	void updateMember(ModifyMember member) throws SQLException;

	public String login(Member m);

	public void saveToken(Member m);

	int isLogin(String userId);
	void addLoginCheck(String userId);
	void updateLoginCondition(String userId);

}
