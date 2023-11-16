package com.ssafy.enjoy.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.board.model.Board;
import com.ssafy.enjoy.board.model.Page;
import com.ssafy.enjoy.board.model.mapper.BoardMapper;
import com.ssafy.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<Board> getList(Page page) throws Exception {
		try {
			List<Board> list = null;
			page.setStart(page.getPgno() * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE);
			page.setEnd(SizeConstant.LIST_SIZE);
			if (page.getKey() != null) {
				page.setWord("%" + page.getWord() + "%");
				if (page.getKey().equals("subjet")) {
					list = boardMapper.readBoardWithSubject(page);
				} else if (page.getKey().equals("content")) {
					list = boardMapper.readBoardWithContent(page);
				} else if (page.getKey().equals("user_id")) {
					list = boardMapper.readBoardWithUserId(page);
				} else {
					throw new Exception("worng key");
				}
			} else {
				list = boardMapper.readBoard(page);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public void writeBoard(Board board) throws Exception {
		try {
			boardMapper.createBoard(board);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public Board getDetail(Board board) throws Exception {
		try {
			boardMapper.updateHit(board);
			return boardMapper.readBoardDetail(board);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public void modifyBoard(Board board) throws Exception {
		try {
			boardMapper.updateBoard(board);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public void deleteBoard(Board board) throws Exception {
		try {
			boardMapper.deleteMapper(board);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public int getPageNum(Page page) throws Exception {
		try {
			if (page.getKey() != null) {
				page.setWord("%" + page.getWord() + "%");
				if (page.getKey().equals("subject")) {
					int pages = boardMapper.countBoardWithSubject(page);
					return  pages/ SizeConstant.LIST_SIZE+1;
				} else if (page.getKey().equals("content")) {
					return boardMapper.countBoardWithContent(page) / SizeConstant.LIST_SIZE+1;
				} else if (page.getKey().equals("user_id")) {
					return boardMapper.countBoardWithUserId(page) / SizeConstant.LIST_SIZE+1;
				} else {
					throw new Exception("worng key");
				}
			} else {
				return boardMapper.countBoard() / SizeConstant.LIST_SIZE+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

}
