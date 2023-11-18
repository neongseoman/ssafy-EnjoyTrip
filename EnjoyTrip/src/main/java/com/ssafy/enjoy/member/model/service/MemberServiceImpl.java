package com.ssafy.enjoy.member.model.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.member.model.IdInfo;
import com.ssafy.enjoy.member.model.KeyInfo;
import com.ssafy.enjoy.member.model.LoginTry;
import com.ssafy.enjoy.member.model.Member;
import com.ssafy.enjoy.member.model.ModifyMember;
import com.ssafy.enjoy.member.model.mapper.IdInfoMapper;
import com.ssafy.enjoy.member.model.mapper.KeyInfoMapper;
import com.ssafy.enjoy.member.model.mapper.LogintryMapper;
import com.ssafy.enjoy.member.model.mapper.MemberMapper;
import com.ssafy.util.OpenCrypt;
import org.springframework.transaction.annotation.Transactional;

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
	public Member loginMember(Member member, String ip) throws Exception {
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
			Member userinfo = memberMapper.readMember(member.getUserId(), hashed_cUserPwd);
			if (userinfo == null) {
				logintryMapper.updateLointryFail(loginTry.getClientIp(), loginTry.getUserId());
				throw new Exception("wrong password");
			}
			logintryMapper.updateLogintrySuccess(loginTry.getClientIp(), loginTry.getUserId());
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
	public Member sessionLogin(Member member) throws Exception {
		// 로그인 시도했는데 id가 없다고 유저가 없다고 알려주는 것은 적절하지 않은 것 같음.
		// 그냥 실패하면 아이디나 패스워드 둘 중 하나 틀렸다고 하는게 맞는 것 같음.
		IdInfo idInfo = idInfoMapper.readIdInfo(member.getUserId());
		String hashed_id = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(member.getUserId(), idInfo.getSalt()));
		KeyInfo keyInfo = keyInfoMapper.readKeyInfo(hashed_id);
		byte[] key = OpenCrypt.hexToByteArray(keyInfo.getKey());
		String cUserPwd = OpenCrypt.aesEncrypt(member.getUserPassword(), key);
		String hashed_cUserPwd = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(cUserPwd, keyInfo.getSalt()));
		Member userinfo = memberMapper.readMember(member.getUserId(), hashed_cUserPwd);
		if (userinfo == null){
//			throw new Exception("")
		}
		int is_login = memberMapper.isLogin(member.getUserId());
		if (is_login == 1) {
			throw new Exception("이미 로그인 했는데?");
		}
		return userinfo;
	}

	@Override
	public int idCheck(String id) throws Exception {
		try {
			System.out.println(id);
			int ids = memberMapper.idCheck(id);
			System.out.println(ids);
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

}
