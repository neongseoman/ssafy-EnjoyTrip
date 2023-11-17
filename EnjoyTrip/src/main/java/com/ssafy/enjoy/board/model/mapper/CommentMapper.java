package com.ssafy.enjoy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.board.model.Board;
import com.ssafy.enjoy.board.model.Comment;

@Mapper
public interface CommentMapper {

	List<Comment> readComment(Board board) throws SQLException;

	void createComment(Comment comment) throws SQLException;
	
	void deleteComment(Comment comment) throws SQLException;
}
