package com.ssafy.enjoy.board.model.service;

import java.util.List;

import com.ssafy.enjoy.board.model.dto.BoardDto;
import com.ssafy.enjoy.board.model.dto.PageDto;
import com.ssafy.enjoy.board.model.dto.PositionDto;
import com.ssafy.enjoy.board.model.vo.BoardVo;
import com.ssafy.enjoy.board.model.vo.PositionVo;

public interface BoardService {

	List<BoardVo> getList(PageDto page) throws Exception;

	void writeBoard(BoardDto board, List<PositionDto> positions) throws Exception;

	BoardVo getDetail(BoardDto board) throws Exception;

	void modifyBoard(BoardDto board, List<PositionDto> positions) throws Exception;

	void deleteBoard(BoardDto board) throws Exception;

	int getPageNum(PageDto page) throws Exception ;

	List<PositionVo> getPositions(BoardDto board) throws Exception;

}
