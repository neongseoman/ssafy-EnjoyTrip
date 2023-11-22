package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.vo.KeyInfoVo;
import com.ssafy.util.Exception.VOException;

@Mapper
public interface KeyInfoMapper {

	KeyInfoVo readKeyInfo(String hashed_id) throws SQLException, VOException;

	void createKeyInfo(KeyInfoVo keyInfoVo) throws SQLException;

	void deleteKeyInfo(String hashedId) throws SQLException;

}
