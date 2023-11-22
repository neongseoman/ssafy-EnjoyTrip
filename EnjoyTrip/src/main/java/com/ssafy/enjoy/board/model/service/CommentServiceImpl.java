package com.ssafy.enjoy.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.board.model.dto.BoardDto;
import com.ssafy.enjoy.board.model.dto.CommentDto;
import com.ssafy.enjoy.board.model.mapper.CommentMapper;
import com.ssafy.enjoy.board.model.vo.CommentVo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	

	@Override
	public void writeComment(CommentDto comment) throws Exception {
		try {
			commentMapper.createComment(comment);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public void deleteComment(CommentDto comment) throws Exception {
		try {
			commentMapper.deleteComment(comment);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public List<CommentVo> getList(BoardDto board) throws Exception {
		try {
			return commentMapper.readComment(board);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

}
