package com.ssafy.enjoy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.board.model.dto.BoardDto;
import com.ssafy.enjoy.board.model.dto.CommentDto;
import com.ssafy.enjoy.board.model.vo.CommentVo;

@Mapper
public interface CommentMapper {

	List<CommentVo> readComment(BoardDto board) throws SQLException;

	void createComment(CommentDto comment) throws SQLException;
	
	void deleteComment(CommentDto comment) throws SQLException;
}
