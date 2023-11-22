package com.ssafy.enjoy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.board.model.dto.PositionDto;
import com.ssafy.enjoy.board.model.vo.PositionVo;

@Mapper
public interface PositionMapper {

	void createPosition(PositionDto position) throws SQLException;

	List<PositionVo> readPositions(int articleNo) throws SQLException;

	void deletePositions(int articleNo) throws SQLException;

}
