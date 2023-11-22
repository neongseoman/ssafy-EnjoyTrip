package com.ssafy.enjoy.board.model.service;

import java.util.List;

import com.ssafy.enjoy.board.model.dto.BoardDto;
import com.ssafy.enjoy.board.model.dto.CommentDto;
import com.ssafy.enjoy.board.model.vo.CommentVo;

public interface CommentService {

	List<CommentVo> getList(BoardDto board) throws Exception;

	void writeComment(CommentDto comment) throws Exception;

	void deleteComment(CommentDto comment) throws Exception;

}
