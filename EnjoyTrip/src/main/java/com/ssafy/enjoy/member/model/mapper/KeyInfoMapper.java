package com.ssafy.enjoy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.member.model.KeyInfo;

@Mapper
public interface KeyInfoMapper {

	KeyInfo readKeyInfo(String hashed_id) throws SQLException;

	void createKeyInfo(KeyInfo keyInfo) throws SQLException;

	void deleteKeyInfo(String hashedId) throws SQLException;

}
