package idol.back.board.repository;

import idol.back.board.application.domain.Board;
import idol.back.board.application.domain.BoardType;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class BoardMemoryRepository implements BoardRepository{

    private final Map<Integer, Board> boards = new HashMap<>();
    private static Integer sequence = 0;

    @Override
    public Integer save(Board board) {
        boards.put(++sequence, board);
        board.setId(sequence);
        return board.getId();
    }

    @Override
    public Optional<Board> findBoardIdByType(BoardType type) {
        return boards.values().stream()
                .filter(board -> board.getType() == type)
                .findAny();
    }

    @Override
    public Collection<Board> findAll(){
        return boards.values();
    }
}
