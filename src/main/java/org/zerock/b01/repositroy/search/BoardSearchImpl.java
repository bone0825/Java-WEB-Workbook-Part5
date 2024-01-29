package org.zerock.b01.repositroy.search;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.QBoard;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; //Q도메인 객체
        JPQLQuery<Board> query = from(board);
        query.where(board.title.contains("1"));

        //paging
        this.getQuerydsl().applyPagination(pageable,query);

        List<Board> list = query.fetch(); //JPQLQuery 실행
        long count = query.fetchCount(); // count 쿼리 실행

        return null;
    }
}
