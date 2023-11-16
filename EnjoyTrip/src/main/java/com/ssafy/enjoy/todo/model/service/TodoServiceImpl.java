package com.ssafy.enjoy.todo.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.todo.model.Schedule;
import com.ssafy.enjoy.todo.model.mapper.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoMapper todoMapper;

	@Override
	public List<Schedule> getList(Schedule schedule) throws Exception {
		try {
			return todoMapper.readTodos(schedule);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("로드 실패");
		}
	}

	@Override
	public void putList(List<Schedule> todos) throws Exception {
		try {
			for (int i = 0; i < todos.size(); i++) {
				todoMapper.createTodo(todos.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("업로드 실패");
		}
	}

	@Override
	public void deleteTodo(Schedule schedule) throws Exception {
		try {
			todoMapper.deleteTodo(schedule);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("삭제 실패");
		}
	}

}
