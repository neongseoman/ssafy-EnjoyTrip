package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.IdInfo;

@Mapper
public interface IdInfoMapper {

	IdInfo readIdInfo(String id) throws SQLException;

	void createIdInfo(IdInfo idInfo) throws SQLException;

	void deleteIdInfo(String userId) throws SQLException;

}
