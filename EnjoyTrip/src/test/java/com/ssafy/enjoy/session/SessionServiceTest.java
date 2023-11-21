package com.ssafy.enjoy.session;

import com.ssafy.enjoy.session.model.SessionReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SessionServiceTest {

    @Autowired
    SessionService sessionService;

    @Test
    @DisplayName("1과 2를 더하면 3이 나온다")
    void 세션_서비스_테스트() {
        assertThat(sessionService.isSessionValid("asd")).isFalse();
    }

    @Test
    @DisplayName("session req to node test")
    void sessionReqTest() throws IOException {
        SessionReqDto reqTestModel = new SessionReqDto("test");
        assertThat(sessionService.sessionReq(reqTestModel)).isInstanceOf(String.class);
    }

}