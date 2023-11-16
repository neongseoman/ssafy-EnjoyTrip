package com.ssafy.enjoy.todo.model.service;

import java.util.List;

import com.ssafy.enjoy.todo.model.Schedule;

public interface TodoService {

	List<Schedule> getList(Schedule schedule) throws Exception;

	void putList(List<Schedule> todos) throws Exception;

}
