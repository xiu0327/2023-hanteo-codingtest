package idol.back.board.repository;

import idol.back.board.application.domain.Board;
import idol.back.board.application.domain.BoardType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BoardMemoryRepository implements BoardRepository{

    private final Map<Integer, Board> boards = new HashMap<>();
    private static Integer sequence = 0;

    @Override
    public Integer save(Board board) {
        if (board.getType() == BoardType.ANONYMOUS){
            if (existAnonymousBoard()){
                return findBoardIdByType(BoardType.ANONYMOUS);
            }
        }
        boards.put(++sequence, board);
        board.setId(sequence);
        return board.getId();
    }

    @Override
    public Integer findBoardIdByType(BoardType type) {
        Board result = boards.values().stream()
                .filter(board -> board.getType() == type)
                .findAny()
                .orElseThrow(RuntimeException::new);
        return result.getId();
    }

    private Boolean existAnonymousBoard(){
        return boards.values().stream()
                .anyMatch(board -> board.getType() == BoardType.ANONYMOUS);
    }
}
