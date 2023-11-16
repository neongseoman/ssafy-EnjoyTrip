package com.ssafy.enjoy.todo.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.todo.model.Schedule;

@Mapper
public interface TodoMapper {

	List<Schedule> readTodos(Schedule schedule) throws SQLException;

	void createTodo(Schedule schedule) throws SQLException;

	void deleteTodo(Schedule schedule) throws SQLException;

}
