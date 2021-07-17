package mingu.inflearn.service;

import lombok.RequiredArgsConstructor;
import mingu.inflearn.domain.Board;
import mingu.inflearn.framework.data.domain.PageRequestParameter;
import mingu.inflearn.parameter.BoardParameter;
import mingu.inflearn.parameter.BoardSearchParameter;
import mingu.inflearn.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter) {
        return boardRepository.getList(pageRequestParameter);
    }

    public Board get(int boardSeq) {
        return boardRepository.get(boardSeq);
    }

    public void save(BoardParameter board) {
        Board before = boardRepository.get(board.getBoardSeq());
        if (before == null) {
            boardRepository.save(board);
        } else {
            boardRepository.update(board);
        }
    }

    public void delete(int boardSeq) {
        boardRepository.delete(boardSeq);
    }

    public void saveList1(List<BoardParameter> list) {
        for (BoardParameter parameter : list) {
            boardRepository.save(parameter);
        }
    }

    public void saveList2(List<BoardParameter> list) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("boardList", list);
        boardRepository.saveList(paramMap);
    }
}
