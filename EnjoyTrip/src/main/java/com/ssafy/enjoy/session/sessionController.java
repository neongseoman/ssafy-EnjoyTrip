package com.ssafy.enjoy.session;

import com.google.type.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// 노드로부터 오는 http를 받기 위한 컨트롤러.
@RestController
@RequestMapping("session")
public class sessionController {

    @Autowired
    SessionService sessionService;

    @PostMapping("invalidate")
    public void invalidate(@RequestBody Map<String,String> hash_data){
        if(sessionService.nodeReqInvalidate(hash_data.get("sessionId")))
            System.out.println(hash_data.toString() + " is removed");
        System.out.println(hash_data + " is not removed");
    }
}
