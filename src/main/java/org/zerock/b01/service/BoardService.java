package org.zerock.b01.service;

import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

public interface BoardService {

    //글 등록하기
    Long register(BoardDTO boardDTO);

    //조회 하기 return DTO
    BoardDTO readOne(Long bno); //Service to Controller

    //수정 작업
    void modify(BoardDTO boardDTO);
    //삭제 작업
    void remove(Long bno);

    //목록, 검색 작업
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
