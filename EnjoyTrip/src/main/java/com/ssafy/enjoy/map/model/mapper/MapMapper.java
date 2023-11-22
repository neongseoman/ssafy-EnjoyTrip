package com.ssafy.enjoy.map.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.map.dto.MyMapDto;
import com.ssafy.enjoy.map.vo.MyMapVo;
import com.ssafy.util.VOException;

@Mapper
public interface MapMapper {

	List<MyMapVo> readSido() throws SQLException, VOException;

	List<MyMapVo> readGugun() throws SQLException, VOException;

	List<MyMapVo> readMap(MyMapDto map) throws SQLException, VOException;

}
