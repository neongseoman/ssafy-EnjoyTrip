package com.ssafy.enjoy.map.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.map.vo.MapDescVo;

@Mapper
public interface DescMapper {

	MapDescVo readDesc(int contentId) throws SQLException ;
	
}
