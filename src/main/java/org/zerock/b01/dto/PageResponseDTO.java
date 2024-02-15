package org.zerock.b01.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

//페이지 목록과 시작 페이지/끝 페이지 등에 대한 처리
@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        //존재하는 글이 없음
        if(total <= 0) return;

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(this.page/10.0)) * 10; //화면에서 마지막 번호
        this.start = this.end - (this.size-1); //화면에서 시작 번호

        int last = (int) (Math.ceil((total/(double)size))); //전체 데이터 바탕 마지막 번호

        this.end = end > last ? last : end;

        this.prev = this.start > 1;
        this.next = total > this.end*this.size;
    }

}
