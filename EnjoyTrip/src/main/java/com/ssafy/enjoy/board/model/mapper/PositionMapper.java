package com.ssafy.enjoy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.board.model.Position;

@Mapper
public interface PositionMapper {

	void createPosition(Position position) throws SQLException;

	List<Position> readPositions(int articleNo) throws SQLException;

	void deletePositions(int articleNo) throws SQLException;

}
