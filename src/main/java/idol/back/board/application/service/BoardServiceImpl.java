package idol.back.board.application.service;

import idol.back.board.application.domain.Board;
import idol.back.board.application.domain.BoardType;
import idol.back.board.exceptions.BoardExceptionType;
import idol.back.board.repository.BoardRepository;
import idol.back.global.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Integer save(Board board) {
        if (board.getType() == BoardType.ANONYMOUS){
            Optional<Board> result = boardRepository.findBoardIdByType(BoardType.ANONYMOUS);
            if (result.isPresent()){
                return result.get().getId();
            }
        }
        return boardRepository.save(board);
    }
}
