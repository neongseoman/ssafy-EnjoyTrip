package com.ssafy.enjoy.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.board.model.Board;
import com.ssafy.enjoy.board.model.Comment;
import com.ssafy.enjoy.board.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<Comment> getList(Board board) throws Exception {
		try {
			return commentMapper.readComment(board);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public void writeComment(Comment comment) throws Exception {
		try {
			commentMapper.createComment(comment);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public void deleteComment(Comment comment) throws Exception {
		try {
			commentMapper.deleteComment(comment);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

}
