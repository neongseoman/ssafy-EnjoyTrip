package com.ssafy.enjoy.map.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.map.model.MyMap;

@Mapper
public interface MapMapper {

	List<MyMap> readSido() throws SQLException;

	List<MyMap> readGugun() throws SQLException;

	List<MyMap> readMap(MyMap map) throws SQLException;

}
