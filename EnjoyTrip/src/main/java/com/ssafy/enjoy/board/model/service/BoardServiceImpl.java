package com.ssafy.enjoy.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.board.model.dto.BoardDto;
import com.ssafy.enjoy.board.model.dto.PageDto;
import com.ssafy.enjoy.board.model.dto.PositionDto;
import com.ssafy.enjoy.board.model.mapper.BoardMapper;
import com.ssafy.enjoy.board.model.mapper.PositionMapper;
import com.ssafy.enjoy.board.model.vo.BoardVo;
import com.ssafy.enjoy.board.model.vo.PositionVo;
import com.ssafy.util.SizeConstant;
import com.ssafy.util.VOException;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	@Autowired
	PositionMapper positionMapper;

	@Override
	public List<BoardVo> getList(PageDto page) throws Exception {
		try {
			List<BoardVo> list = null;
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
		}catch(VOException e) {
			throw new Exception("Database error");
		}
	}

	@Override
	public void writeBoard(BoardDto board, List<PositionDto> positions) throws Exception {
		try {
			boardMapper.createBoard(board);
			int articleNo = boardMapper.readBoardNo(board);
			for(int i=0;i<positions.size();i++) {
				positions.get(i).setArticleNo(articleNo);
				positionMapper.createPosition(positions.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public BoardVo getDetail(BoardDto board) throws Exception {
		try {
			boardMapper.updateHit(board);
			return boardMapper.readBoardDetail(board);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}catch(VOException e) {
			throw new Exception("Database error");
		}
	}

	@Override
	public void modifyBoard(BoardDto board, List<PositionDto> positions) throws Exception {
		try {
			boardMapper.updateBoard(board);
			int articleNo = board.getArticleNo();
			positionMapper.deletePositions(articleNo);
			for(int i=0;i<positions.size();i++) {
				positions.get(i).setArticleNo(articleNo);
				positionMapper.createPosition(positions.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public void deleteBoard(BoardDto board) throws Exception {
		try {
			positionMapper.deletePositions(board.getArticleNo());
			boardMapper.deleteBoard(board);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public int getPageNum(PageDto page) throws Exception {
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

	@Override
	public List<PositionVo> getPositions(BoardDto board) throws Exception {
		try {
			return positionMapper.readPositions(board.getArticleNo());
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}catch(VOException e) {
			throw new Exception("Database error");
		}
	}

}
