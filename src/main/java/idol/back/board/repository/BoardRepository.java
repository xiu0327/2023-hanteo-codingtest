package idol.back.board.repository;

import idol.back.board.application.domain.Board;
import idol.back.board.application.domain.BoardType;

public interface BoardRepository {
    Integer save(Board board);
    Integer findBoardIdByType(BoardType type);
}
