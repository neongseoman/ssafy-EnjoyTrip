package com.ssafy.enjoy.map.model.service;

import java.util.List;

import com.ssafy.enjoy.map.dto.MyMapDto;
import com.ssafy.enjoy.map.vo.MyMapVo;

public interface MapService {

	List<MyMapVo> getSido() throws Exception;

	List<MyMapVo> getGugun() throws Exception;

	List<MyMapVo> search(MyMapDto map) throws Exception;

}
