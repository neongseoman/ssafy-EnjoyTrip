//package com.ssafy.enjoy.member.model;
//
//import com.ssafy.util.Exception.VOException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Description;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class MemberVOTest {
//
//    MemberVO memberVO = new MemberVO();
//
//    @Test
//    @DisplayName("VO Test")
//    void memberVo_Test1() throws VOException {
//        assertThat(memberVO.setEmailDomain("....asdfasdf.asd")).isFalse();
//    }
//
//
//    @Test
//    @DisplayName("VO Test")
//    void memberVo_Test2() throws VOException {
//        assertThat(memberVO.setEmailDomain("com")).isFalse();
//    }
//
//
//    @Test
//    @DisplayName("VO Test")
//    void memberVo_Test3() throws VOException {
//        assertThat(memberVO.setEmailDomain("asdf''ff.com")).isFalse();
//    }
//
//    @Test
//    @DisplayName("VO Test")
//    void memberVo_Test4() throws VOException {
//        assertThat(memberVO.setEmailDomain("asdf!@#$%^&*&((ff.com")).isFalse();
//    }
//
//    @Test
//    @DisplayName("VO Test")
//    void memberVo_Test5() throws VOException {
//        assertThat(memberVO.setEmailDomain(".comasdft")).isFalse();
//    }
//
//    @Test
//    @DisplayName("VO Test")
//    void memberVo_Test6() throws VOException {
//        assertThat(memberVO.setEmailDomain("kakao.com")).isTrue();
//    }
//}