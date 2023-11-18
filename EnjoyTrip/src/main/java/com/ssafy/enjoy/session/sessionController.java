package com.ssafy.enjoy.session;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// 노드로부터 오는 http를 받기 위한 컨트롤러.
@RestController
@RequestMapping("session")
public class sessionController {

    @PostMapping("invalidate")
    public void invalidate(@RequestBody Map<String,String> hash_data){
        System.out.println(hash_data.toString() + " is delicated");
    }
}
