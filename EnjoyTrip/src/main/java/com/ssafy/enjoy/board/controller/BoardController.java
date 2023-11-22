package com.ssafy.enjoy.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.board.model.dto.BoardDto;
import com.ssafy.enjoy.board.model.dto.PageDto;
import com.ssafy.enjoy.board.model.dto.PositionDto;
import com.ssafy.enjoy.board.model.service.BoardService;
import com.ssafy.enjoy.board.model.vo.BoardVo;
import com.ssafy.enjoy.board.model.vo.PositionVo;
import com.ssafy.util.DtoException;

@RestController
@RequestMapping("/YZ97gY92")
@CrossOrigin(origins = "*")
public class BoardController {

	@Autowired
	BoardService boardService;

	@PostMapping("/Ct6X83dL")
	public Map<String, Object> getList(@RequestBody PageDto page) throws DtoException {
		Map<String, Object> result = new HashMap<String, Object>();
		if (page.getPgno() == 0) {
			page.setPgno(1);
		}
		try {
			List<BoardVo> list = boardService.getList(page);
			result.put("msg", "OK");
			result.put("detail", "success to load board");
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to load board");
		}
		return result;
	}

	@PostMapping("/ePQowXNQ")
	public Map<String, Object> writeBoard(@RequestBody Map<String, Object> map, HttpServletRequest request)
			throws DtoException {
		Map boardMap = (Map) map.get("board");
		BoardDto board = new BoardDto();
		board.setContent((String) boardMap.get("content"));
		board.setSubject((String) boardMap.get("subject"));
		board.setUserId((String) request.getAttribute("userId"));
		List positionList = (List) map.get("positions");
		List<PositionDto> positions = new ArrayList<PositionDto>();
		for (int i = 0; i < positionList.size(); i++) {
			Map positionMap = (Map) positionList.get(i);
			PositionDto pos = new PositionDto();
			pos.setLatitude((double) positionMap.get("latitude"));
			pos.setLongitude((double) positionMap.get("longitude"));
			positions.add(pos);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (board.getUserId().equals("") || board.getContent().equals("") || board.getSubject().equals("")) {
			result.put("msg", "NO");
			result.put("detail", "게시글의 내용을 입력해 주세요");
		} else {
			try {
				boardService.writeBoard(board, positions);
				result.put("msg", "OK");
				result.put("detail", "Success to write board");
			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to write board");
			}
		}
		return result;
	}

	@PostMapping("/KPVnFhFX")
	public Map<String, Object> getDetail(@RequestBody BoardDto board) {
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(board);
		if (board.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "게시글을 불러올 수 없습니다.");
		} else {
			try {
				List<PositionVo> positions = boardService.getPositions(board);
				BoardVo boardResult = boardService.getDetail(board);
				result.put("msg", "OK");
				result.put("detail", "Success to write board detail");
				result.put("board", boardResult);
				result.put("positions", positions);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to write board detail");
			}
		}
		return result;
	}

	@PostMapping("/7QJMgsU7")
	public Map<String, Object> modifyBoard(@RequestBody Map<String, Object> map, HttpServletRequest request)
			throws DtoException {
		Map boardMap = (Map) map.get("board");
		BoardDto board = new BoardDto();
		board.setArticleNo((int) boardMap.get("articleNo"));
		board.setContent((String) boardMap.get("content"));
		board.setSubject((String) boardMap.get("subject"));
		board.setUserId((String) request.getAttribute("userId"));
		List positionList = (List) map.get("positions");
		List<PositionDto> positions = new ArrayList<PositionDto>();
		for (int i = 0; i < positionList.size(); i++) {
			Map positionMap = (Map) positionList.get(i);
			PositionDto pos = new PositionDto();
			pos.setLatitude((double) positionMap.get("latitude"));
			pos.setLongitude((double) positionMap.get("longitude"));
			positions.add(pos);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (board.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "게시글을 수정할 수 없습니다.");
		} else {
			try {
				boardService.modifyBoard(board, positions);
				result.put("msg", "OK");
				result.put("detail", "Success to modify board detail");
			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to modify board detail");
			}
		}
		return result;
	}

	@PostMapping("/S1BLjFsA")
	public Map<String, Object> deleteBoard(@RequestBody BoardDto board, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (board.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "게시글을 삭제할 수 없습니다.");
		} else {
			try {
				board.setUserId((String) request.getAttribute("userId"));
				boardService.deleteBoard(board);
				PageDto page = new PageDto();
				result.put("msg", "OK");
				result.put("detail", "Success to delete board detail");
			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to delete board detail");
			}
		}
		return result;
	}

	@PostMapping("/t7OqJjRE")
	public Map<String, Object> getPageNum(@RequestBody PageDto page) {
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(page);
		try {
			int pages = boardService.getPageNum(page);
			result.put("msg", "OK");
			result.put("detail", "Success to load page nation");
			result.put("pages", pages);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to load page nation");
		}
		return result;
	}
}
