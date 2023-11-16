package com.ssafy.enjoy.map.model.service;

import java.util.List;

import com.ssafy.enjoy.map.model.MyMap;

public interface MapService {

	List<MyMap> getSido() throws Exception;

	List<MyMap> getGugun() throws Exception;

	List<MyMap> search(MyMap map) throws Exception;

}
