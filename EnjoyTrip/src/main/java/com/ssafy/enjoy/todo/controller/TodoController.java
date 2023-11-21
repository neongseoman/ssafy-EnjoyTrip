package com.ssafy.enjoy.todo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.todo.model.Schedule;
import com.ssafy.enjoy.todo.model.service.TodoService;

@RestController
@RequestMapping("/SVIhWrLV")
public class TodoController {
	@Autowired
	TodoService todoService;
	
	@PostMapping("/Crh17XqZ")
	public Map<String, Object> download(@RequestBody Schedule schedule){
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(schedule);
		try {
			List<Schedule> list = todoService.getList(schedule);
			result.put("msg", "OK");
			result.put("detail", "할일 목록 로드 성공");
			result.put("list", list);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "할일 목록을 로드한느데 실패 했습니다.");
		}
		return result;
	}
	
	@PostMapping("/PmtI1NmN")
	public Map<String, Object> upload(@RequestBody Map<String, Object> map){
		List<Map> todos = (List)map.get("todos");
		String userId = (String)map.get("userId");
		List<Schedule> todo_list = new ArrayList<Schedule>();
		for(Map todo : todos) {
			Schedule temp = new Schedule();
			temp.setUserId((String)todo.get("user_id"));
			temp.setDate((String)todo.get("date"));
			temp.setTime((String)todo.get("time"));
			temp.setTitle((String)todo.get("title"));
			temp.setCompleted((boolean)todo.get("completed"));
			todo_list.add(temp);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			todoService.putList(todo_list, userId);
			result.put("msg", "OK");
			result.put("detail", "할일 목록 업로드 성공");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "할일 목록을 업로드한느데 실패 했습니다.");
		}
		return result;
	}
}
