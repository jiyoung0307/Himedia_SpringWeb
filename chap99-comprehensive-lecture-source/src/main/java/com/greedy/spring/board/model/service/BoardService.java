package com.greedy.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.spring.board.model.dto.BoardDTO;
import com.greedy.spring.board.model.dto.ReplyDTO;
import com.greedy.spring.common.exception.board.BoardRegistException;
import com.greedy.spring.common.exception.board.ReplyRegistException;
import com.greedy.spring.common.exception.board.ReplyRemoveException;
import com.greedy.spring.common.exception.board.ThumbnailRegistException;
import com.greedy.spring.common.paging.SelectCriteria;
import com.greedy.spring.notice.model.dto.NoticeDTO;

public interface BoardService {

	int selectTotalCount(Map<String, String> searchMap);

	List<BoardDTO> selectBoardList(SelectCriteria selectCriteria);

	void registBoard(BoardDTO board) throws BoardRegistException;
	
	BoardDTO selectBoardDetail(int no);

	List<ReplyDTO> registReply(ReplyDTO registReply) throws ReplyRegistException ;
	
	List<ReplyDTO> removeReply(ReplyDTO removeReply) throws ReplyRemoveException;
	
	List<ReplyDTO> selectAllReplyList(int boardNo);
	
	List<BoardDTO> selectAllThumbnailList();

	void registThumbnail(BoardDTO thumbnail) throws ThumbnailRegistException;

	BoardDTO selectThumbnailDetail(int no);


}
