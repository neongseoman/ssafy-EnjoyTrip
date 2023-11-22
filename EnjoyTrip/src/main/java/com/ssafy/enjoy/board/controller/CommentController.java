package com.ssafy.enjoy.board.controller;

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
import com.ssafy.enjoy.board.model.dto.CommentDto;
import com.ssafy.enjoy.board.model.service.CommentService;
import com.ssafy.enjoy.board.model.vo.CommentVo;

@RestController
@RequestMapping("/B1Fc0O5K")
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/qSg1WWvf")
	public Map<String, Object> getList(@RequestBody BoardDto board) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (board.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "fail to load comment");
		} else {
			try {
				List<CommentVo> list = commentService.getList(board);
				result.put("msg", "OK");
				result.put("detail", "fail to load comment");
				result.put("list", list);

			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to load comment");
			}
		}
		return result;
	}

	@PostMapping("/ndKw8G69")
	public Map<String, Object> writeComment(@RequestBody CommentDto comment, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (comment.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "fail to load comment");
		} else {
			try {
				comment.setUserId((String)request.getAttribute("userId"));
				comment.setUserName((String)request.getAttribute("userName"));
				commentService.writeComment(comment);
				result.put("msg", "OK");
				result.put("detail", "fail to load comment");

			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", "fail to load comment");
			}
		}
		return result;
	}
	@PostMapping("/cBHoXtQ5")
	public Map<String, String> delete(@RequestBody CommentDto comment, HttpServletRequest request){
		Map<String, String> result = new HashMap<String, String>();
		try {
			comment.setUserId((String)request.getAttribute("userId"));
			comment.setUserName((String) request.getAttribute("userName"));
			commentService.deleteComment(comment);
			result.put("msg", "OK");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to delete comment");
		}
		return result;
	}
}
