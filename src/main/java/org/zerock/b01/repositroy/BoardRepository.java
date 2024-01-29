package org.zerock.b01.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Board;
import org.zerock.b01.repositroy.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch {

}
