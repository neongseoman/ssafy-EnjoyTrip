package com.ssafy.enjoy.map.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.map.model.MyMap;
import com.ssafy.enjoy.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	MapMapper mapMapper;

	@Override
	public List<MyMap> getSido() throws Exception {
		try {
			return mapMapper.readSido();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public List<MyMap> getGugun() throws Exception {
		try {
			return mapMapper.readGugun();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public List<MyMap> search(MyMap map) throws Exception {
		try {
			return mapMapper.readMap(map);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}
	
}
