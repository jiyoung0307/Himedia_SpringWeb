package com.greedy.spring.member.model.dao;

import com.greedy.spring.member.model.dto.MemberDTO;

public interface MemberMapper {

	String selectMemberById(String userId);
	
	int insertMember(MemberDTO member);

	String selectEncryptedPwd(MemberDTO member);

	MemberDTO selectMember(MemberDTO member);

	int updateMember(MemberDTO member);

	int deleteMember(MemberDTO member);

}
