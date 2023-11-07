package com.greedy.spring.notice.model.dao;

import java.util.List;

import com.greedy.spring.notice.model.dto.NoticeDTO;

public interface NoticeMapper {

	List<NoticeDTO> selectAllNoticeList();

	int incrementNoticeCount(int no);

	int insertNotice(NoticeDTO notice);
	
	NoticeDTO selectNoticeDetail(int no);

	int updateNotice(NoticeDTO notice);

	int deleteNotice(int no);

}
