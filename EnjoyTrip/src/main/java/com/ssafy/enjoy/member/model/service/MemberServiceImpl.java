package com.ssafy.enjoy.member.model.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoy.member.model.IdInfo;
import com.ssafy.enjoy.member.model.KeyInfo;
import com.ssafy.enjoy.member.model.LoginTry;
import com.ssafy.enjoy.member.model.Member;
import com.ssafy.enjoy.member.model.MemberVO;
import com.ssafy.enjoy.member.model.ModifyMember;
import com.ssafy.enjoy.member.model.mapper.IdInfoMapper;
import com.ssafy.enjoy.member.model.mapper.KeyInfoMapper;
import com.ssafy.enjoy.member.model.mapper.LogintryMapper;
import com.ssafy.enjoy.member.model.mapper.MemberMapper;
import com.ssafy.util.OpenCrypt;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	LogintryMapper logintryMapper;

	@Autowired
	IdInfoMapper idInfoMapper;

	@Autowired
	KeyInfoMapper keyInfoMapper;

	@Override
	public MemberVO loginMember(Member member, String ip) throws Exception {
		try {
			LoginTry loginTry = logintryMapper.readLoginTry(ip, member.getUserId());
			if (loginTry != null) {
				Date today = new Date();
				Time now = new Time(today.getHours(), today.getMinutes() - 30, today.getSeconds());
				if (loginTry.getRetry() >= 5 && today == loginTry.getLastTryDate()
						&& loginTry.getLastTryTime().after(now)) {
					throw new Exception("login try limit 30min");
				}
			} else {
				logintryMapper.createLogintry(ip, member.getUserId());
				loginTry = logintryMapper.readLoginTry(ip, member.getUserId());
			}
			if (memberMapper.idCheck(member.getUserId()) != 1) {
//				System.out.println(member.getUserId());
				throw new Exception("no such user");
			}
			IdInfo idInfo = idInfoMapper.readIdInfo(member.getUserId());
			String hashed_id = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(member.getUserId(), idInfo.getSalt()));
			KeyInfo keyInfo = keyInfoMapper.readKeyInfo(hashed_id);
			byte[] key = OpenCrypt.hexToByteArray(keyInfo.getKey());
			String cUserPwd = OpenCrypt.aesEncrypt(member.getUserPassword(), key);
			String hashed_cUserPwd = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(cUserPwd, keyInfo.getSalt()));
			MemberVO userinfo = memberMapper.readMember(member.getUserId(), hashed_cUserPwd);
			if (userinfo == null) {
				logintryMapper.updateLointryFail(loginTry.getClientIp(), loginTry.getUserId());
				throw new Exception("wrong password");
			}
			logintryMapper.updateLogintrySuccess(loginTry.getClientIp(), loginTry.getUserId());
//			System.out.println(userinfo);
			return userinfo;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public int idCheck(String id) throws Exception {
		try {
//			System.out.println(id);
			int ids = memberMapper.idCheck(id);
//			System.out.println(ids);
			return ids;
		} catch (SQLException e) {
//			return 0;
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Transactional
	@Override
	public void joinMember(Member member) throws Exception {
		try {
			if (idCheck(member.getUserId()) != 0) {
				throw new Exception("이미 존재하는 사용자");
			}
			IdInfo idInfo = new IdInfo();
			idInfo.setId(member.getUserId());
			idInfo.setSalt(UUID.randomUUID().toString());
			byte[] hashed_id_byte= OpenCrypt.getSHA256(idInfo.getId(), idInfo.getSalt());
			String hashed_id = OpenCrypt.byteArrayToHex(hashed_id_byte);
			KeyInfo keyInfo = new KeyInfo();
			keyInfo.setHashedId(hashed_id);
			byte[] key_byte = OpenCrypt.generateKey("AES", 128);
			keyInfo.setKey(OpenCrypt.byteArrayToHex(key_byte));
			keyInfo.setSalt(UUID.randomUUID().toString());
			String key_crypt = OpenCrypt.aesEncrypt(member.getUserPassword(), key_byte);
			byte[] key_hashed_byte = OpenCrypt.getSHA256(key_crypt, keyInfo.getSalt());
			member.setUserPassword(OpenCrypt.byteArrayToHex(key_hashed_byte));
			memberMapper.createMember(member);
			memberMapper.addLoginCheck(member.getUserId());
			idInfoMapper.createIdInfo(idInfo);
			keyInfoMapper.createKeyInfo(keyInfo);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public void updateMember(ModifyMember member) throws Exception {
		try {
			IdInfo idInfo = idInfoMapper.readIdInfo(member.getUserId());
			byte[] hashed_id_byte = OpenCrypt.getSHA256(idInfo.getId(), idInfo.getSalt());
			String hashed_id = OpenCrypt.byteArrayToHex(hashed_id_byte);
			KeyInfo keyInfo = keyInfoMapper.readKeyInfo(hashed_id);
			String pw_crypt = OpenCrypt.aesEncrypt(member.getNewPassword(),OpenCrypt.hexToByteArray(keyInfo.getKey()));
			byte[] pw_hashed_byte = OpenCrypt.getSHA256(pw_crypt, keyInfo.getSalt());
			String pw_hashed = OpenCrypt.byteArrayToHex(pw_hashed_byte);
			member.setNewPassword(pw_hashed);
			System.out.println(member);
			memberMapper.updateMember(member);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

	@Override
	public int isLogin(String id) throws Exception {
		return memberMapper.isLogin(id);
	}

	@Override
	public void updateLoginCondition(String userId) throws Exception {
		memberMapper.updateLoginCondition(userId);
	}

	public void logout(String id)  throws Exception{
		System.out.println(id);
		memberMapper.updateLoginCondition(id);
	}

	@Override
	public void deleteMember(Member member) throws Exception {
		try {
			memberMapper.deleteLoginCondition(member.getUserId());
			memberMapper.deleteMember(member.getUserId());
			IdInfo idinfo = idInfoMapper.readIdInfo(member.getUserId());
			idInfoMapper.deleteIdInfo(member.getUserId());
			byte[] hashedIdByte = OpenCrypt.getSHA256(idinfo.getId(), idinfo.getSalt());
			String hashedId = OpenCrypt.byteArrayToHex(hashedIdByte);
			keyInfoMapper.deleteKeyInfo(hashedId);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Server error");
		}
	}

}
