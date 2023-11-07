package com.greedy.spring.notice.model.service;

import java.util.List;

import com.greedy.spring.common.exception.notice.NoticeModifyException;
import com.greedy.spring.common.exception.notice.NoticeRegistException;
import com.greedy.spring.common.exception.notice.NoticeRemoveException;
import com.greedy.spring.notice.model.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> selectAllNoticeList();

	void registNotice(NoticeDTO notice) throws NoticeRegistException;
	
	NoticeDTO selectNoticeDetail(int no);

	void modifyNotice(NoticeDTO notice) throws NoticeModifyException;

	void removeNotice(int no) throws NoticeRemoveException;

}
