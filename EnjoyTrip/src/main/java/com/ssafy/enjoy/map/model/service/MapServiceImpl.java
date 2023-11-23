package com.ssafy.enjoy.map.model.service;

import java.util.List;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.map.model.dto.MyMapDto;
import com.ssafy.enjoy.map.model.mapper.MapMapper;
import com.ssafy.enjoy.map.vo.MyMapVo;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	MapMapper mapMapper;

	@Override
	public List<MyMapVo> getSido() throws Exception {
		try {
			return mapMapper.readSido();
		}catch(MyBatisSystemException e) {
			throw new Exception("Database error");
		}
	}

	@Override
	public List<MyMapVo> getGugun() throws Exception {
		try {
			return mapMapper.readGugun();
		}catch(MyBatisSystemException e) {
			throw new Exception("Database error");
		}
	}

	@Override
	public List<MyMapVo> search(MyMapDto map) throws Exception {
		try {
			return mapMapper.readMap(map);
		}catch(MyBatisSystemException e) {
			e.printStackTrace();
			throw new Exception("Database error");
		}
	}
	
}
