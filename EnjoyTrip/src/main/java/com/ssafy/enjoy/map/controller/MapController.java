package com.ssafy.enjoy.map.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.map.model.MapDesc;
import com.ssafy.enjoy.map.model.MyMap;
import com.ssafy.enjoy.map.model.service.DescService;
import com.ssafy.enjoy.map.model.service.MapService;

@RestController
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	MapService mapService;
	@Autowired
	DescService descService;
	
	
	@GetMapping("/sido")
	public Map<String, Object> getSido(){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<MyMap> list = mapService.getSido();
			result.put("msg", "OK");
			result.put("detail","loaded city info");
			result.put("list", list);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to load city info");
		}
		return result;
	}
	
	@GetMapping("/gugun")
	public Map<String, Object> getGugun(){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<MyMap> list = mapService.getGugun();
			result.put("msg", "OK");
			result.put("detail", "loaded section info");
			result.put("list", list);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to load section info");
		}
		return result;
	}
	
	@PostMapping("/search")
	public Map<String, Object> search(@RequestBody MyMap map){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List list = mapService.search(map);
			result.put("msg", "OK");
			result.put("detail", "loaded info");
			result.put("list", list);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to load info");
		}
		return result;
	}
	@PostMapping("/detail")
	public Map<String, Object> detail(@RequestBody MyMap map){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MapDesc desc = descService.getDetail(map);
			result.put("msg", "OK");
			result.put("detail", "loaded info");
			
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to load info");
		}
		return result;
	}
}
