package com.greedy.spring.board.model.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greedy.spring.member.model.dto.MemberDTO;

public class ReplyDTO {
	private int no;
	private int refBoardNo;
	private String body;
	private int writerMemberNo;
	private MemberDTO writer;		            // MemberTable과 join하는 경우 1:1 조인이 될 것이기 때문에 MemberDTO 타입으로 생성
	private String status;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")	// 댓글 jackson을 통한 ajax 처리 시 날짜 포맷 설정용 어노테이션 추가
	private Date createdDate;
	
	public ReplyDTO() {}
	
	public ReplyDTO(int no, int refBoardNo, String body, int writerMemberNo, MemberDTO writer, String status,
			Date createdDate) {
		this.no = no;
		this.refBoardNo = refBoardNo;
		this.body = body;
		this.writerMemberNo = writerMemberNo;
		this.writer = writer;
		this.status = status;
		this.createdDate = createdDate;
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getRefBoardNo() {
		return refBoardNo;
	}
	
	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public int getWriterMemberNo() {
		return writerMemberNo;
	}
	
	public void setWriterMemberNo(int writerMemberNo) {
		this.writerMemberNo = writerMemberNo;
	}
	
	public MemberDTO getWriter() {
		return writer;
	}
	
	public void setWriter(MemberDTO writer) {
		this.writer = writer;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Override
	public String toString() {
		return "ReplyDTO [no=" + no + ", refBoardNo=" + refBoardNo + ", body=" + body + ", writerMemberNo="
				+ writerMemberNo + ", writer=" + writer + ", status=" + status + ", createdDate=" + createdDate + "]";
	}
}
