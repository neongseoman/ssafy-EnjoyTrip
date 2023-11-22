package com.ssafy.enjoy.board.model.service;

import java.util.List;

import org.mybatis.spring.MyBatisSystemException;
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
		} catch(MyBatisSystemException e) {
			throw new Exception("Database error");
		}
	}

	@Override
	public void deleteComment(CommentDto comment) throws Exception {
		try {
			commentMapper.deleteComment(comment);
		}catch(MyBatisSystemException e) {
			throw new Exception("Database error");
		}
	}

	@Override
	public List<CommentVo> getList(BoardDto board) throws Exception {
		try {
			return commentMapper.readComment(board);
		}catch(MyBatisSystemException e) {
			throw new Exception("Database error");
		}
	}

}
