package idol.back.board.repository;

import idol.back.board.application.domain.Board;
import idol.back.board.application.domain.BoardType;

import java.util.Collection;
import java.util.Optional;

public interface BoardRepository {
    Integer save(Board board);
    Optional<Board> findBoardIdByType(BoardType type);
    Collection<Board> findAll();
}
