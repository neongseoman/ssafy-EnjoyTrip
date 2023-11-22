package com.ssafy.enjoy.todo.model.service;

import java.util.List;

import com.ssafy.enjoy.todo.model.dto.ScheduleDto;
import com.ssafy.enjoy.todo.model.vo.ScheduleVo;

public interface TodoService {

	List<ScheduleVo> getList(ScheduleDto schedule) throws Exception;

	void putList(List<ScheduleDto> todos, String userId) throws Exception;

}
