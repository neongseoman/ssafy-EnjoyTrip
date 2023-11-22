package com.ssafy.enjoy.map.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.map.dto.MyMapDto;
import com.ssafy.enjoy.map.model.mapper.MapMapper;
import com.ssafy.enjoy.map.vo.MyMapVo;
import com.ssafy.util.VOException;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	MapMapper mapMapper;

	@Override
	public List<MyMapVo> getSido() throws Exception {
		try {
			return mapMapper.readSido();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}catch(VOException e) {
			throw new Exception("Database error");
		}
	}

	@Override
	public List<MyMapVo> getGugun() throws Exception {
		try {
			return mapMapper.readGugun();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}catch(VOException e) {
			throw new Exception("Database error");
		}
	}

	@Override
	public List<MyMapVo> search(MyMapDto map) throws Exception {
		try {
			return mapMapper.readMap(map);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}catch(VOException e) {
			throw new Exception("Database error");
		}
	}
	
}
