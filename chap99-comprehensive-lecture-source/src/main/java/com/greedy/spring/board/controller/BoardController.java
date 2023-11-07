package com.greedy.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.greedy.spring.board.model.dto.BoardDTO;
import com.greedy.spring.board.model.dto.ReplyDTO;
import com.greedy.spring.board.model.service.BoardService;
import com.greedy.spring.common.exception.board.BoardRegistException;
import com.greedy.spring.common.exception.board.ReplyRegistException;
import com.greedy.spring.common.exception.board.ReplyRemoveException;
import com.greedy.spring.common.paging.Pagenation;
import com.greedy.spring.common.paging.SelectCriteria;
import com.greedy.spring.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/list")
	public ModelAndView  ctList(HttpServletRequest request, ModelAndView mv) {
		
		/* 
		 * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
		 * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
		 */
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		System.out.println("컨트롤러에서 검색조건 확인하기 : " + searchMap);
		
		/* 
		 * 전체 게시물 수가 필요하다.
		 * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
		 * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
		 */
		int totalCount = boardService.selectTotalCount(searchMap);
		
		System.out.println("totalBoardCount : " + totalCount);
		
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.
		
		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;
		
		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		System.out.println(selectCriteria);
		
		/* 조회해 온다 */
		List<BoardDTO> boardList = boardService.selectBoardList(selectCriteria);
		
		System.out.println("boardList : " + boardList);
		
		mv.addObject("boardList", boardList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("/board/boardList");
		
		return mv;
	}
	
	@GetMapping("/regist")
	public void registBoard() {}
	
	@PostMapping("/regist")
	public String registBoard(@ModelAttribute BoardDTO board, HttpServletRequest request, 
			RedirectAttributes rttr) throws BoardRegistException {
		int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
//		String title = request.getParameter("title");
//		String body = request.getParameter("body");
		int writerMemberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		board.setCategoryCode(categoryCode);
//		newBoard.setTitle(title);
//		newBoard.setBody(body);
		board.setWriterMemberNo(writerMemberNo);
		
		boardService.registBoard(board);
		
		rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다!");
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/detail")
	public String selectBoardDetail(HttpServletRequest request, Model model) {
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDTO boardDetail = boardService.selectBoardDetail(no);

		model.addAttribute("board", boardDetail);
		
		/* 댓글 작성 완료 후 추가할 것 */
		List<ReplyDTO> replyList = boardService.selectAllReplyList(no);
		model.addAttribute("replyList", replyList);
		
		return "/board/boardDetail";
	}
	
	@PostMapping(value="/registReply", produces="application/json; charset=UTF-8")		
	@ResponseBody
	public List<ReplyDTO> registReply(@ModelAttribute ReplyDTO registReply,
			HttpServletRequest request) throws JsonProcessingException, ReplyRegistException {
		registReply.setWriterMemberNo(((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		List<ReplyDTO> replyList = boardService.registReply(registReply);
		
		return replyList;
	}
	
	@PostMapping(value="/removeReply", produces="application/json; charset=UTF-8")		
	@ResponseBody
	public List<ReplyDTO> removeReply(@ModelAttribute ReplyDTO removeReply) throws JsonProcessingException, ReplyRemoveException {
		List<ReplyDTO> replyList = boardService.removeReply(removeReply);
		
		return replyList;
	}
}
