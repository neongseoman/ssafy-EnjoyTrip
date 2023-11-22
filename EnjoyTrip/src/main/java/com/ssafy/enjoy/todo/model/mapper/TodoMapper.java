package com.ssafy.enjoy.todo.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoy.todo.model.dto.ScheduleDto;
import com.ssafy.enjoy.todo.model.vo.ScheduleVo;
import com.ssafy.util.VOException;

@Mapper
public interface TodoMapper {

	List<ScheduleVo> readTodos(ScheduleDto schedule) throws SQLException, VOException;

	void createTodo(ScheduleDto schedule) throws SQLException;

	void deleteTodo(String userId) throws SQLException;

}
