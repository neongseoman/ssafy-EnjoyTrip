package com.ssafy.enjoy.board.model.service;

import java.util.List;

import com.ssafy.enjoy.board.model.Board;
import com.ssafy.enjoy.board.model.Page;

public interface BoardService {

	List<Board> getList(Page page) throws Exception;

	void writeBoard(Board board) throws Exception;

	Board getDetail(Board board) throws Exception;

	void modifyBoard(Board board) throws Exception;

	void deleteBoard(Board board) throws Exception;

	int getPageNum(Page page) throws Exception ;

}
