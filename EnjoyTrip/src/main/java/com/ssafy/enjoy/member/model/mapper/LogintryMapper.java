package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.vo.LoginTryVo;

@Mapper
public interface LogintryMapper {
	
	LoginTryVo readLoginTry(String ip, String id) throws SQLException;

	void updateLointryFail(String ip, String id) throws SQLException;

	void updateLogintrySuccess(String ip, String id) throws SQLException;

	void createLogintry(String ip, String id) throws SQLException;
}
