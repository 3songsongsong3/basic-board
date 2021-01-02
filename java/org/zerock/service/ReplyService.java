package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;

public interface ReplyService {
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
	public List<ReplyVO> getList(Criteria cri, Long bno);
	public int modify(ReplyVO vo);
	public int remove(Long rno);
	public ReplyVO get(Long rno);
	public int register(ReplyVO vo);
}










