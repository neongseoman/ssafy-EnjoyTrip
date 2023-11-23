package com.ssafy.enjoy.todo.model.service;

import java.util.List;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.todo.model.dto.ScheduleDto;
import com.ssafy.enjoy.todo.model.mapper.TodoMapper;
import com.ssafy.enjoy.todo.model.vo.ScheduleVo;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoMapper todoMapper;

	@Override
	public List<ScheduleVo> getList(ScheduleDto schedule) throws Exception {
		try {
			return todoMapper.readTodos(schedule);
		}catch(MyBatisSystemException e) {
			e.printStackTrace();
			throw new Exception("Database error");
		}
	}

	@Override
	public void putList(List<ScheduleDto> todos, String userId) throws Exception {
		try {
			todoMapper.deleteTodo(userId);
			for (int i = 0; i < todos.size(); i++) {
				todoMapper.createTodo(todos.get(i));
			}
		} catch(MyBatisSystemException e) {
			throw new Exception("Database error");
		}
	}


}
