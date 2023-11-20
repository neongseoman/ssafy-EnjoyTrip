package com.ssafy.enjoy.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.board.model.Board;
import com.ssafy.enjoy.board.model.Page;
import com.ssafy.enjoy.board.model.Position;
import com.ssafy.enjoy.board.model.service.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*")
public class BoardController {

	@Autowired
	BoardService boardService;

	@PostMapping("/list")
	public Map<String, Object> getList(@RequestBody Page page) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(page.getPgno()==0) {
			page.setPgno(1);
		}
		if("".equals(page.getKey())) {
			page.setKey(null);
		}
		try {
			List<Board> list = boardService.getList(page);
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

	@PostMapping("/write")
	public Map<String, Object> writeBoard(@RequestBody Map<String, Object> map) {
		Map boardMap = (Map) map.get("board");
		Board board = new Board();
		board.setContent((String)boardMap.get("content"));
		board.setSubject((String)boardMap.get("subject"));
		board.setUserId((String)boardMap.get("userId"));
		List positionList = (List) map.get("positions");
		List<Position> positions = new ArrayList<Position>();
		for(int i=0;i<positionList.size();i++) {
			Map positionMap = (Map)positionList.get(i);
			Position pos = new Position();
			pos.setLatitude((double)positionMap.get("latitude"));
			pos.setLongitude((double)positionMap.get("longitude"));
			positions.add(pos);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (board.getUserId() == null || board.getContent() == null || board.getSubject() == null||"".equals(board.getUserId())||"".equals(board.getContent())||"".equals(board.getSubject())) {
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
	
	@PostMapping("/detail")
	public  Map<String, Object> getDetail(@RequestBody Board board){
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(board);
		if(board.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "게시글을 불러올 수 없습니다.");
		}else {
			try {
				List<Position> positions = boardService.getPositions(board);
				board = boardService.getDetail(board);
				result.put("msg", "OK");
				result.put("detail", "Success to write board detail");
				result.put("board", board);
				result.put("positions", positions);
			}catch(Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to write board detail");
			}
		}
		return result;
	}
	
	@PostMapping("/modify")
	public  Map<String, Object> modifyBoard(@RequestBody Map<String, Object> map){
		Map boardMap = (Map) map.get("board");
		Board board = new Board();
		board.setContent((String)boardMap.get("content"));
		board.setSubject((String)boardMap.get("subject"));
		board.setUserId((String)boardMap.get("userId"));
		List positionList = (List) map.get("positions");
		List<Position> positions = new ArrayList<Position>();
		Map<String, Object> result = new HashMap<String, Object>();
		if(board.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "게시글을 수정할 수 없습니다.");
		}else {
			try {
				boardService.modifyBoard(board, positions);
				result.put("msg", "OK");
				result.put("detail", "Success to modify board detail");
			}catch(Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to modify board detail");
			}
		}
		return result;
	}
	
	@PostMapping("/delete")
	public  Map<String, Object> deleteBoard(@RequestBody Board board){
		Map<String, Object> result = new HashMap<String, Object>();
		if(board.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "게시글을 삭제할 수 없습니다.");
		}else {
			try {
				boardService.deleteBoard(board);
				Page page = new Page();
				result.put("msg", "OK");
				result.put("detail", "Success to delete board detail");
			}catch(Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to delete board detail");
			}
		}
		return result;
	}
	
	@PostMapping("/page")
	public Map<String, Object> getPageNum(@RequestBody Page page){
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(page);
		if("".equals(page.getKey())) {
			page.setKey(null);
		}
		try {
			int pages = boardService.getPageNum(page);
			result.put("msg", "OK");
			result.put("detail", "Success to load page nation");
			result.put("pages", pages);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to load page nation");
		}
		return result;
	}
}
