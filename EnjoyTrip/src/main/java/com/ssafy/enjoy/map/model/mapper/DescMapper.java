package com.ssafy.enjoy.map.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.map.model.MapDesc;

@Mapper
public interface DescMapper {

	MapDesc readDesc(int contentId) throws SQLException ;
	
}
