package com.greedy.spring.member.model.service;

import com.greedy.spring.common.exception.member.LoginFailedException;
import com.greedy.spring.common.exception.member.MemberModifyException;
import com.greedy.spring.common.exception.member.MemberRegistException;
import com.greedy.spring.common.exception.member.MemberRemoveException;
import com.greedy.spring.member.model.dto.MemberDTO;

public interface MemberService {

	boolean selectMemberById(String userId);
	
	void registMember(MemberDTO member) throws MemberRegistException;

	MemberDTO findMember(MemberDTO member) throws LoginFailedException;

	void modifyMember (MemberDTO member) throws MemberModifyException;

	void removeMember(MemberDTO member) throws MemberRemoveException;
}
