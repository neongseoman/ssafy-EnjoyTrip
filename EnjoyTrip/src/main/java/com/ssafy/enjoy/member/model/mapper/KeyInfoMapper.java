package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import com.ssafy.enjoy.member.model.vo.KeyInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KeyInfoMapper {

	KeyInfoVo readKeyInfo(String hashed_id) throws SQLException;

	void createKeyInfo(KeyInfoVo keyInfoVo) throws SQLException;

	void deleteKeyInfo(String hashedId) throws SQLException;

}
