package com.ssafy.enjoy.board.controller;

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
import com.ssafy.enjoy.board.model.Comment;
import com.ssafy.enjoy.board.model.service.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/list")
	public Map<String, Object> getList(@RequestBody Board board) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (board.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "fail to load comment");
		} else {
			try {
				List<Comment> list = commentService.getList(board);
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

	@PostMapping("/write")
	public Map<String, Object> writeComment(@RequestBody Comment comment) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (comment.getArticleNo() == 0) {
			result.put("msg", "NO");
			result.put("detail", "fail to load comment");
		} else {
			try {
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
	@PostMapping("/delete")
	public Map<String, String> delete(@RequestBody Comment comment){
		Map<String, String> result = new HashMap<String, String>();
		System.out.println(result);
		try {
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
