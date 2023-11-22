package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.vo.IdInfoVo;

@Mapper
public interface IdInfoMapper {

	IdInfoVo readIdInfo(String id) throws SQLException;

	void createIdInfo(IdInfoVo idInfoVo) throws SQLException;

	void deleteIdInfo(String userId) throws SQLException;

}
