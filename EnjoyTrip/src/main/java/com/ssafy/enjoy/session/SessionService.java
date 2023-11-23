package com.ssafy.enjoy.session;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssafy.enjoy.member.model.mapper.MemberMapper;
import com.ssafy.enjoy.member.model.vo.MemberVo;
import com.ssafy.enjoy.session.model.SessionModel;
import com.ssafy.enjoy.session.model.SessionReqDto;


@Service
public class SessionService { // 세션은 빈번하게 사용되니까
	@Autowired
	MemberMapper memberMapper;
    private static SessionService instance;
    private SessionService(){}
    public static SessionService getInstance(){
        if (instance == null){
            instance = new SessionService();
        }
        return instance;
    }


    static Map<String, SessionModel> session = new HashMap<>();

    @Value("${NODE-URL}")
    private String NODE_URL;

    @Description("node에 세션을 요청함. sessionId를 받고 비동기 요청을 함.")
    public String sessionReq(SessionReqDto reqBody, MemberVo userInfo, String hashedUserAgent) throws IOException {
        System.out.println("into session req:" + reqBody.toString());

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application","json", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        String sessionId = restTemplate.postForObject(NODE_URL+"/session", reqBody, String.class);
        SessionModel sessionModel
                = new SessionModel(reqBody.getUserId(),userInfo.getUserName(), sessionId, hashedUserAgent,0, LocalDateTime.now());

        session.put(sessionId,sessionModel);
        System.out.println("this is session : " + session.get(sessionId));
        return sessionId;
    }

    @Description("this session is valid? live? not null?")
    public boolean isSessionValid(String sessionId){
        // session key - value is live?
        if (session.get(sessionId) == null) {
            return false;
        }
        SessionModel sessionModel = session.get(sessionId);
//        Duration duration1 = Duration.between(LocalDateTime.now(),sessionModel.getLatelyAccessTime());
//        System.out.println("현재와 마지막 접근 시간의 차이"+duration1.getSeconds());
//        Duration duration2 = Duration.between(LocalDateTime.now().minusMinutes(30),sessionModel.getLatelyAccessTime());
//        System.out.println("현재와 마지막 접근 시간의 차이"+duration2.getSeconds());
//        System.out.println(LocalDateTime.now()+" : "+LocalDateTime.now().minusMinutes(30));
//        System.out.println(sessionModel.getLatelyAccessTime());
//        System.out.println(LocalDateTime.now().minusMinutes(30).isBefore(sessionModel.getLatelyAccessTime()));

//         현재 시간 -30분 이 마지막 접근 시간보다 뒤에 있다면 세션을 갱신했으니 invalidate하지 않는다.
        // request is before 30m from lastAccessTime
        if (!LocalDateTime.now().minusMinutes(30).isBefore(sessionModel.getLatelyAccessTime())){
            return false;
        }
        return true;
    }

    @Description("HashMap get Value")
    public SessionModel getSession(String sessionId){
        try{
            if (isSessionValid(sessionId))
                return session.get(sessionId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    return null;
    }

    @Description("이게 오면 그냥 바로 삭제 => logout.")
    public boolean invalidate(String sessionId){
        if (isSessionValid(sessionId)){
        	memberMapper.logoutMember(session.get(sessionId).getUserId());
            session.remove(sessionId);
            return true;
        }
        return false;
    }

    //노드에서 invalidate시간 됐다고 요청한거라 마지막 요청으로부터
    // 30분이 지나지 않았다면 무효로하고 시간 차이 후에 다시 invalidate 보내라고 해야함.
    public boolean nodeReqInvalidate(String sessionId){
        if (!isSessionValid(sessionId)){ //이미 세션이 없어...
            return false;
        }
        SessionModel sessionModel = session.get(sessionId);
        // 마지막 접근으로부터 30분이 지났다...
        // 네트워크 에러나 노드의 딜레이로 인해서 조금 늦더라도 시간 여유를 주겠음.
        if (!LocalDateTime.now().minusMinutes(32).isBefore(sessionModel.getLatelyAccessTime())){
            Duration duration = Duration.between(LocalDateTime.now(),sessionModel.getLatelyAccessTime());
            int second = duration.getNano();
            Map<String, String> invalidRequest = new HashMap<>();
            invalidRequest.put("session_id",sessionId);
            invalidRequest.put("user_id", sessionModel.getUserId());
            invalidRequest.put("requestTime", String.valueOf(second));

            // invalidate Message Request to session-manager(node)
            // request time : upper duration.nano time
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = new MediaType("application","json", StandardCharsets.UTF_8);
            headers.setContentType(mediaType);
            String response = restTemplate.postForObject(NODE_URL+"/session/invalidRequest", invalidRequest, String.class);

            return false;
        }
        System.out.println("session 삭제");
        memberMapper.logoutMember(sessionModel.getUserId());
        session.remove(sessionId);
        return true;
    }
}
