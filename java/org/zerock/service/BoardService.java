package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	public List<BoardAttachVO> getAttachList(Long bno); 	//첨부파일 목록 가져오기
	public int getTotalCount(Criteria cri);		//전체 게시물 수 가져오기
	public void register(BoardVO board);		//게시물 추가
	public boolean modify(BoardVO board);		//게시물 수정
	public boolean remove(Long bno);			//게시물 삭제
	public BoardVO get(Long bno);				//게시물 조회
//	public List<BoardVO> getList();				//게시물 전체 조회
	public List<BoardVO> getList(Criteria cri);	//게시물 전체 조회 - 페이징
}









