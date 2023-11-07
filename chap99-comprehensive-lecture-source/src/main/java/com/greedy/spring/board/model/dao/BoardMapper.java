package com.greedy.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.spring.board.model.dto.AttachmentDTO;
import com.greedy.spring.board.model.dto.BoardDTO;
import com.greedy.spring.board.model.dto.ReplyDTO;
import com.greedy.spring.common.paging.SelectCriteria;
import com.greedy.spring.notice.model.dto.NoticeDTO;

public interface BoardMapper {

	int selectTotalCount(Map<String, String> searchMap);

	List<BoardDTO> selectBoardList(SelectCriteria selectCriteria);

	int insertBoard(BoardDTO board);

	BoardDTO selectBoardDetail(int no);
	
	int insertReply(ReplyDTO registedReply);
	
	List<ReplyDTO> selectReplyList(int refBoardNo);
	
	List<BoardDTO> selectAllThumbnailList();

	int insertThumbnailContent(BoardDTO thumbnail);

	int insertAttachment(AttachmentDTO attachmentDTO);

	int incrementBoardCount(int no);

	BoardDTO selectThumbnailDetail(int no);

	int deleteReply(int replyNo);

}
