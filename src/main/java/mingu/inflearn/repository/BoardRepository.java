package mingu.inflearn.repository;

import mingu.inflearn.domain.Board;
import mingu.inflearn.framework.data.domain.PageRequestParameter;
import mingu.inflearn.parameter.BoardParameter;
import mingu.inflearn.parameter.BoardSearchParameter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardRepository {
    List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter);
    Board get(int boardSeq);
    void save(BoardParameter board);
    void saveList(Map<String, Object> paramMap);
    void update(BoardParameter board);
    void delete(int boardSeq);
}
