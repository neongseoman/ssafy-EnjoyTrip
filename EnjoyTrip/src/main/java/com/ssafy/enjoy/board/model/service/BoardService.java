package com.ssafy.enjoy.board.model.service;

import java.util.List;

import com.ssafy.enjoy.board.model.Board;
import com.ssafy.enjoy.board.model.Page;
import com.ssafy.enjoy.board.model.Position;

public interface BoardService {

	List<Board> getList(Page page) throws Exception;

	void writeBoard(Board board, List<Position> positions) throws Exception;

	Board getDetail(Board board) throws Exception;

	void modifyBoard(Board board) throws Exception;

	void deleteBoard(Board board) throws Exception;

	int getPageNum(Page page) throws Exception ;

	List<Position> getPositions(Board board) throws Exception;

}
