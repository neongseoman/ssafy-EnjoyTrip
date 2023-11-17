package com.ssafy.enjoy.board.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.board.model.Position;

@Mapper
public interface PositionMapper {

	void createPosition(Position position) throws SQLException;

}
