package com.ssafy.enjoy.board.model.service;

import java.util.List;

import com.ssafy.enjoy.board.model.Board;
import com.ssafy.enjoy.board.model.Comment;

public interface CommentService {

	List<Comment> getList(Board board) throws Exception;

	void writeComment(Comment comment) throws Exception;

}
