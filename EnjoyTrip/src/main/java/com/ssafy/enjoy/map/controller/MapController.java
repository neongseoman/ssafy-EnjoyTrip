package com.ssafy.enjoy.map.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.map.model.dto.MyMapDto;
import com.ssafy.enjoy.map.model.service.DescService;
import com.ssafy.enjoy.map.model.service.MapService;
import com.ssafy.enjoy.map.vo.MapDescVo;
import com.ssafy.enjoy.map.vo.MyMapVo;

@RestController
@RequestMapping("/MytfEaEE")
public class MapController {
	
	@Autowired
	MapService mapService;
	@Autowired
	DescService descService;
	
	
	@PostMapping("/vQ1gaZml")
	public Map<String, Object> getSido(){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<MyMapVo> list = mapService.getSido();
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
	
	@PostMapping("/j4nwRBe9")
	public Map<String, Object> getGugun(){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<MyMapVo> list = mapService.getGugun();
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
	
	@PostMapping("/hqJImwm8")
	public Map<String, Object> search(@RequestBody MyMapDto map){
		System.out.println(map);
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
	@PostMapping("/Lnbkmq9x")
	public Map<String, Object> detail(@RequestBody MyMapVo map){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MapDescVo desc = descService.getDetail(map.getContent_id());
			result.put("msg", "OK");
			result.put("detail", "loaded info");
			result.put("desc", desc);
			
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "NO");
			result.put("detail", "fail to load info");
		}
		return result;
	}
}
