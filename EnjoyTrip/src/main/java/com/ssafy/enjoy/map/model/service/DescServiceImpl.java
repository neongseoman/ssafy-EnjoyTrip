package com.ssafy.enjoy.map.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.map.model.mapper.DescMapper;
import com.ssafy.enjoy.map.vo.MapDescVo;
import com.ssafy.util.VOException;

@Service
public class DescServiceImpl implements DescService {

	@Autowired
	DescMapper descMapper;
	
	@Override
	public MapDescVo getDetail(int content_id) throws Exception {
		try {
			return descMapper.readDesc(content_id);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server Error");
		}catch(VOException e) {
			throw new Exception("Database error");
		}
	}

}
