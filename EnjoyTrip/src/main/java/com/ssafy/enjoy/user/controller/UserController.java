package com.ssafy.enjoy.user.controller;


import com.ssafy.enjoy.user.model.service.OAuthService;
import com.ssafy.enjoy.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    OAuthService oAuthService;
    @Autowired
    UserService userService;

    @GetMapping("kakaoLogin")
    public Map<String, Object> kakaoLogin(@RequestParam String code, HttpServletRequest request, HttpServletResponse response){
        System.out.println(code);
        String access_Token=oAuthService.getKaKaoAccessToken(code);
        System.out.println(access_Token);
        System.out.println("kakaoLogin");
        String email = oAuthService.createKakaoUser(access_Token);
//        System.out.println("clear");
        if (email != null){
            System.out.println("User Controller + " + email);
//            String[] tokens = userService.jwtLogin(email,email);
//            if (tokens.length > 0){
                Map<String, Object> resMap =new HashMap();
                resMap.put("tokens","ok");
                return resMap;
//            } else { // why no token?
//                return null;
//            }
        }
        return null;
    }

    @PostMapping("jwtLogin")
    public Map<String, Object> jwtLogin(@RequestBody Map<String, String> reqMap){
        System.out.println(reqMap);

        //db,,

        String name = "박근수";
        String id = (String) reqMap.get("id");
        if (name != null) {
            String[] tokens = userService.jwtLogin(name,id);
            if (tokens.length > 0){
                Map<String, Object> resMap =new HashMap();
                resMap.put("tokens",tokens);
                return resMap;
            } else { // why no token?
                return null;
            }
        }

        return null;
    }
//
//    private void jwtCreate(String na)
}
