package com.ssafy.enjoy.map.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.map.model.MapDesc;
import com.ssafy.enjoy.map.model.mapper.DescMapper;

@Service
public class DescServiceImpl implements DescService {

	@Autowired
	DescMapper descMapper;
	
	@Override
	public MapDesc getDetail(int content_id) throws Exception {
		try {
			return descMapper.readDesc(content_id);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server Error");
		}
	}

}
